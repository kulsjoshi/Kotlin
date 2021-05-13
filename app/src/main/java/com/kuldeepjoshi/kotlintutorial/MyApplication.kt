package com.kuldeepjoshi.kotlintutorial

import android.app.Application
import com.kuldeepjoshi.kotlintutorial.dependencyInjection.appDependencies
import com.kuldeepjoshi.kotlintutorial.roomDatabase.database.WordRoomDatabase
import com.kuldeepjoshi.kotlintutorial.roomDatabase.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@MyApplication)
            modules(appDependencies)
        }
    }

    /**
     * Below code is for roomDatabase package
     *
     * Using by lazy so the database and the repository are only created when they're needed
     * rather than when the application starts
     */

    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }

}