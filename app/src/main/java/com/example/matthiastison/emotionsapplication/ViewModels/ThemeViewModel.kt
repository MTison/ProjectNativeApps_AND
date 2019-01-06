package com.example.matthiastison.emotionsapplication.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.WorkerThread
import com.example.matthiastison.emotionsapplication.App
import com.example.matthiastison.emotionsapplication.Database.EmotionsAppRepository
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import javax.inject.Inject

class ThemeViewModel : ViewModel() {

    @Inject
    lateinit var themeRepository: EmotionsAppRepository

    init {
        App.dbComponent.inject(this)
    }

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

    fun getThemeIdOnTitle(themeTitle: String): LiveData<String> {
        return themeRepository.getThemeIdOnTitle(themeTitle)
    }

}
