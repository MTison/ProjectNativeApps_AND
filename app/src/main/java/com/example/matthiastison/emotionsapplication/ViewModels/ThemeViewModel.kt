package com.example.matthiastison.emotionsapplication.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.matthiastison.emotionsapplication.Database.EmotionsAppRepository
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity

// androidViewModel automatically passes down the application context, it is needed to instantiate our db instance
class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val themeRepository: EmotionsAppRepository = EmotionsAppRepository(application)

    private val allThemes: LiveData<ArrayList<ThemeEntity>> = themeRepository.getAllThemes

    @WorkerThread
    fun insert(themeEntity: ThemeEntity) {
        themeRepository.insertTheme(themeEntity)
    }

    @WorkerThread
    fun update(themeEntity: ThemeEntity) {
        themeRepository.updateTheme(themeEntity)
    }

    @WorkerThread
    fun delete(themeEntity: ThemeEntity) {
        themeRepository.deleteTheme(themeEntity)
    }

    fun getAllThemes(): LiveData<ArrayList<ThemeEntity>> {
        return allThemes
    }

    fun getTheme(id: Int): LiveData<ThemeEntity> {
        return themeRepository.getTheme(id)
    }

    fun getThemeIdOnTitle(themeTitle: String): LiveData<Int> {
        return themeRepository.getThemeIdOnTitle(themeTitle)
    }

}
