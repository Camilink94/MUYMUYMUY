package com.camilink.rrhh

import android.app.Application
import com.camilink.rrhh.di.appModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
            androidContext(this@MyApplication)
        }

        Stetho.initializeWithDefaults(this)
    }

}