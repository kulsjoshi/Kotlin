package com.kuldeepjoshi.kotlintutorial.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kuldeepjoshi.kotlintutorial.roomDatabase.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * =================
 * What is the DAO?
 * =================
    In the DAO (data access object), you specify SQL queries and associate them with method calls.
    The compiler checks the SQL and generates queries from convenience annotations for common queries,
    such as @Insert. Room uses the DAO to create a clean API for your code.

    The DAO must be an interface or abstract class.
    The @Dao annotation identifies it as a DAO class for Room.

    By default, all queries must be executed on a separate thread.

    Room has Kotlin coroutines support. This allows your queries to be annotated with the suspend
    modifier and then called from a coroutine or from another suspension function.

 * =================
 * Implement the DAO
 * =================
    - Getting all words ordered alphabetically
    - Inserting a word
    - Deleting all words
 */
@Dao
interface WordDao {

    /**
     * fun getAlphabetizedWords(): List<Word>: A method to get all the words and have it return
     * a List of Words.
     *
     * @Query("SELECT * FROM word_table ORDER BY word ASC"): Query that returns a list of words
     * sorted in ascending order.
     *
     * =================
     * What is Flow?
     * =================
     * A Flow is an async sequence of values
     *
     * Flow produces values one at a time (instead of all at once) that can generate values
     * from async operations like network requests, database calls, or other async code. It supports
     * coroutines throughout its API, so you can transform a flow using coroutines as well!
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    /**
     * suspend fun insert(word: Word) : Declares a suspend function to insert one word.
     *
     * The @Insert annotation is a special DAO method annotation where you don't have to provide
     * any SQL! (There are also @Delete and @Update annotations for deleting and updating rows,
     * but you are not using them in this app.)
     *
     * onConflict = OnConflictStrategy.IGNORE: The selected onConflict strategy ignores a new word
     * if it's exactly the same as one already in the list. To know more about the available conflict
     * strategies, check out the documentation.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    /**
     * suspend fun deleteAll(): Declares a suspend function to delete all the words.
     *
     * There is no convenience annotation for deleting multiple entities, so it's annotated with
     * the generic @Query.
     *
     * @Query("DELETE FROM word_table"): @Query requires that you provide a SQL query as a string
     * parameter to the annotation, allowing for complex read queries and other operations.
     */
    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

}