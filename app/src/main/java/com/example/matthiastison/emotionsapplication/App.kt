package com.example.matthiastison.emotionsapplication

import android.app.Application
import com.example.matthiastison.emotionsapplication.Injection.DaggerDatabaseComponent
import com.example.matthiastison.emotionsapplication.Injection.DatabaseComponent
import com.example.matthiastison.emotionsapplication.Injection.DatabaseModule

class App : Application() {

    companion object {
        lateinit var component : DatabaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerDatabaseComponent
                .builder()
                .databaseModule(DatabaseModule(this))
                .build()
    }

}