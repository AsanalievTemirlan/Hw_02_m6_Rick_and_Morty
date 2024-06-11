package com.example.hw_02_m6_rickmorty

import android.app.Application
import com.example.hw_02_m6_rickmorty.data.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}