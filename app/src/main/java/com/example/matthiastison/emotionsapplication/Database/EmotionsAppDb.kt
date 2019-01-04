package com.example.matthiastison.emotionsapplication.Database

import com.example.matthiastison.emotionsapplication.R
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.support.annotation.WorkerThread
import com.example.matthiastison.emotionsapplication.Database.DAO.SubjectDao
import com.example.matthiastison.emotionsapplication.Database.DAO.ThemeDao
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity

@Database( entities = [SubjectEntity::class, ThemeEntity::class], version = 3)
abstract class EmotionsAppDb : RoomDatabase() {

    // declaring all Data Access Object interfaces
    abstract fun subjectDao(): SubjectDao
    abstract fun themeDao(): ThemeDao

    companion object {
        @Volatile
        private var INSTANCE: EmotionsAppDb? = null

        fun getInstance(context: Context) : EmotionsAppDb =
        // synchronized so only one thread can access the new instance and
        // no duplicate instances are made when 2 threads call this method at the same time
                INSTANCE?: synchronized(this) {
                    INSTANCE?: buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        // make an instance of the database through Room dbBuilder
        fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                    EmotionsAppDb::class.java, "EmotionsApp_database")
                    .fallbackToDestructiveMigration() // deletes and recreates db on version increment
                    .addCallback(object : Callback() {
                        // only gets called once, when db is first created
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            ioThread {
                                getInstance(context).themeDao().insertData(PREPOPULATE_THEME)
                            }
                        }
                    })
                    .build()

        val PREPOPULATE_THEME = listOf(
                ThemeEntity(1,"WONEN",R.color.themeColor1)
                , ThemeEntity(2,"RELATIES",R.color.themeColor2)
                , ThemeEntity(3,"VRIJE TIJD",R.color.themeColor3)
                , ThemeEntity(4,"LEVEN",R.color.themeColor4))
    }
}