package com.example.matthiastison.emotionsapplication.Injection

import android.app.Application
import android.content.Context
import com.example.matthiastison.emotionsapplication.Database.DAO.SubjectDao
import com.example.matthiastison.emotionsapplication.Database.DAO.ThemeDao
import com.example.matthiastison.emotionsapplication.Database.EmotionsAppDb
import com.example.matthiastison.emotionsapplication.Database.EmotionsAppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideEmotionsAppRepository(subjectDao: SubjectDao, themeDao: ThemeDao): EmotionsAppRepository {
        return EmotionsAppRepository(subjectDao, themeDao)
    }

    @Provides
    @Singleton
    internal fun provideSubjectDao(emotionsAppDb: EmotionsAppDb): SubjectDao {
        return emotionsAppDb.subjectDao()
    }

    @Provides
    @Singleton
    internal fun provideThemeDao(emotionsAppDb: EmotionsAppDb): ThemeDao {
        return emotionsAppDb.themeDao()
    }

    @Provides
    @Singleton
    internal fun provideEmotionsAppDb(context: Context): EmotionsAppDb {
        return EmotionsAppDb.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }
}