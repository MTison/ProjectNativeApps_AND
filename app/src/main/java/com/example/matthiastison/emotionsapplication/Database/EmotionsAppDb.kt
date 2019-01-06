package com.example.matthiastison.emotionsapplication.Database

import com.example.matthiastison.emotionsapplication.R
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.matthiastison.emotionsapplication.Database.DAO.SubjectDao
import com.example.matthiastison.emotionsapplication.Database.DAO.ThemeDao
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import org.jetbrains.anko.doAsync
import java.util.*

@Database( entities = [SubjectEntity::class, ThemeEntity::class], version = 9)
abstract class EmotionsAppDb : RoomDatabase() {

    // declaring all Data Access Object interfaces
    abstract fun subjectDao(): SubjectDao
    abstract fun themeDao(): ThemeDao

    companion object {
        @Volatile
        private var INSTANCE: EmotionsAppDb? = null

        fun getDatabase(context: Context): EmotionsAppDb {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            // synchronized so only one thread can access the new instance and
            // no duplicate instances are made when 2 threads call this method at the same time
            synchronized(this) {
                // make an instance of the database through Room dbBuilder
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmotionsAppDb::class.java,
                        "EmotionsApp_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback() {
                            // Get's called when we make a connection with the db
                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                doAsync {
                                    populateDatabase(INSTANCE!!.themeDao())
                                }
                            }
                        }).build()

                // set the instance variable to the made instance
                INSTANCE = instance
                return instance
            }
        }

        fun populateDatabase(themeDao: ThemeDao) {
            var theme = ThemeEntity("WONEN",R.color.themeColor1, "1")
            themeDao.insert(theme)
            theme = ThemeEntity("RELATIES",R.color.themeColor2, "2")
            themeDao.insert(theme)
            theme = ThemeEntity("VRIJE TIJD",R.color.themeColor3, "3")
            themeDao.insert(theme)
            theme = ThemeEntity("LEVEN",R.color.themeColor4, "4")
            themeDao.insert(theme)
        }
    }
}