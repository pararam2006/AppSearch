package com.pararam2006.appsearch

import android.app.Application

class MyApp: Application() {
    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}