package com.example.matthiastison.emotionsapplication

import android.app.Application
import com.example.matthiastison.emotionsapplication.Injection.*

// make dagger components for injection of the necessary dependencies
class App : Application() {

    companion object {
        lateinit var dbComponent : DatabaseComponent
        lateinit var vmComponent : ViewModelComponent
    }

    override fun onCreate() {
        super.onCreate()
        dbComponent = DaggerDatabaseComponent
                .builder()
                .databaseModule(DatabaseModule(this))
                .build()
        vmComponent = DaggerViewModelComponent
                .builder()
                .networkModule(NetworkModule)
                .build()
    }

}