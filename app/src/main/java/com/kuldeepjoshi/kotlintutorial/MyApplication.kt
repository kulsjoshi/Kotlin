package com.kuldeepjoshi.kotlintutorial

import android.app.Application
import com.kuldeepjoshi.kotlintutorial.dependencyInjection.appDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin{
            androidContext(this@MyApplication)
            modules(appDependencies)
        }
    }

}