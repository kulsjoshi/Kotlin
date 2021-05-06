package com.kuldeepjoshi.kotlintutorial

import android.app.Application
import com.kuldeepjoshi.kotlintutorial.dependencyInjection.appDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin{
            androidContext(this@MyApplication)
            modules(appDependencies)
        }
    }

}