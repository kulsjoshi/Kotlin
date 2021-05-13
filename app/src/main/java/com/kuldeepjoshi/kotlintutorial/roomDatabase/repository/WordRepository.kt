package com.kuldeepjoshi.kotlintutorial.roomDatabase.repository

import androidx.annotation.WorkerThread
import com.kuldeepjoshi.kotlintutorial.roomDatabase.dao.WordDao
import com.kuldeepjoshi.kotlintutorial.roomDatabase.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * ======================
 * What is a Repository?
 * ======================
 * A repository class abstracts access to multiple data sources. The repository is not part of the
 * Architecture Components libraries, but is a suggested best practice for code separation and
 * architecture. A Repository class provides a clean API for data access to the rest of the application.
 *
 * ======================
 * Why use a Repository?
 * ======================
 * A Repository manages queries and allows you to use multiple backends. In the most common example,
 * the Repository implements the logic for deciding whether to fetch data from a network or
 * use results cached in a local database.
 *
 * The DAO is passed into the repository constructor as opposed to the whole database. This is
 * because it only needs access to the DAO, since the DAO contains all the read/write methods for
 * the database. There's no need to expose the entire database to the repository.
 *
 * Declares the DAO as a private property in the constructor. Pass in the DAO
 * instead of the whole database, because you only need access to the DAO
 */
class WordRepository(private val wordDao: WordDao) {

    /**
     * Room executes all queries on a separate thread.
     * Observed Flow will notify the observer when the data has changed.
     *
     * The list of words is a public property. It's initialized by getting the Flow list of words
     * from Room; you can do this because of how you defined the getAlphabetizedWords method to
     * return Flow in the "Observing database changes" step. Room executes all queries on a
     * separate thread.
     */
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    /**
     *  By default Room runs suspend queries off the main thread, therefore, we don't need to
     *  implement anything else to ensure we're not doing long running database work
     *  off the main thread.
     *
     *  The suspend modifier tells the compiler that this needs to be called from a coroutine
     *  or another suspending function.
     *
     *  Room executes suspend queries off the main thread.
     */
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}