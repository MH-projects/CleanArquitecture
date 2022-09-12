package com.mh.mhapp

import android.app.Application
import com.mh.mhapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplicationKoin : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplicationKoin)
            modules(listOf(appModule))
        }
    }
}
