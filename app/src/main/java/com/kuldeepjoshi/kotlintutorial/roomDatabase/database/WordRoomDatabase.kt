package com.kuldeepjoshi.kotlintutorial.roomDatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kuldeepjoshi.kotlintutorial.roomDatabase.dao.WordDao
import com.kuldeepjoshi.kotlintutorial.roomDatabase.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * =================
 * What is Room Database
 * =================
 * Room is a database layer on top of an SQLite database.
 *
 * Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
 *
 * Room uses the DAO to issue queries to its database.
 *
 * By default, to avoid poor UI performance, Room doesn't allow you to issue queries on the
 * main thread. When Room queries return Flow, the queries are automatically run
 * asynchronously on a background thread.
 *
 * Room provides compile-time checks of SQLite statements.
 *
 * You annotate the class to be a Room database with @Database and use the annotation parameters
 * to declare the entities that belong in the database and set the version number. Each entity
 * corresponds to a table that will be created in the database. Database migrations are beyond
 * the scope of this codelab, so exportSchema has been set to false here, in order to avoid a
 * build warning. In a real app, consider setting a directory for Room to use to export the schema
 * so you can check the current schema into your version control system.
 *
 */
@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false
)
//The database class for Room must be abstract and extend RoomDatabase.
abstract class WordRoomDatabase : RoomDatabase() {

    /**
     * The database exposes DAOs through an abstract "getter" method for each @Dao.
     */
    abstract fun wordDao(): WordDao

    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        /**
         * getDatabase returns the singleton. It'll create the database the first time it's
         * accessed, using Room's database builder to create a RoomDatabase object in the
         * application context from the WordRoomDatabase class and names it "word_database".
         */
        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {

            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }

        class WordDatabaseCallback(private val coroutineScope: CoroutineScope) :
            RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->

                    coroutineScope.launch {
                        var wordDao = database.wordDao()

                        wordDao.deleteAll()

                        var word = Word("Hello")
                        wordDao.insert(word)

                        word = Word("World!")
                        wordDao.insert(word)

                        word = Word("I am done")
                        wordDao.insert(word)

                    }

                }

            }


        }
    }
}