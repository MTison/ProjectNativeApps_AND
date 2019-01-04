package com.example.matthiastison.emotionsapplication.Database

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.support.annotation.WorkerThread
import com.example.matthiastison.emotionsapplication.Database.DAO.SubjectDao
import com.example.matthiastison.emotionsapplication.Database.DAO.ThemeDao
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import java.util.*

class EmotionsAppRepository(application: Application) {

    private val emotionsDb  : EmotionsAppDb = EmotionsAppDb.getInstance(application)

    private val subjectDao : SubjectDao
    private val themeDao : ThemeDao

    init {
        subjectDao = emotionsDb.subjectDao()
        themeDao = emotionsDb.themeDao()
    }

    val themes = themeDao.getAllThemes()
    val getAllSubjects : LiveData<ArrayList<SubjectEntity>> = subjectDao.getAllSubjects() as LiveData<ArrayList<SubjectEntity>>
    val getAllThemes : LiveData<ArrayList<ThemeEntity>> = themeDao.getAllThemes() as LiveData<ArrayList<ThemeEntity>>

    // SUBJECTS CRUD CALLS
    // ///////////////////////

    @WorkerThread // run DB call asynchronously when writing to DB, to avoid blocking main thread
    fun insertSubject(subjectEntity: SubjectEntity) {
        subjectDao.insert(subjectEntity)
    }

    @WorkerThread
    fun updateSubject(subjectEntity: SubjectEntity) {
        subjectDao.update(subjectEntity)
    }

    @WorkerThread
    fun deleteSubject(subjectEntity: SubjectEntity) {
        subjectDao.delete(subjectEntity)
    }

    fun getSubject(id: String) : LiveData<SubjectEntity> {
        return subjectDao.getSubject(id)
    }

    fun getSubjectsForTheme(themeid: Int) : LiveData<ArrayList<SubjectEntity>> {
        val themeWithSubjects = subjectDao.getSubjectsForTheme(themeid)

        return Transformations.map(themeWithSubjects) {
            it.subjects as ArrayList<SubjectEntity>
        }
    }

    // THEMES CRUD CALLS
    // ///////////////////////

    @WorkerThread
    fun insertTheme(themeEntity: ThemeEntity) {
        themeDao.insert(themeEntity)
    }

    @WorkerThread
    fun updateTheme(themeEntity: ThemeEntity) {
        themeDao.update(themeEntity)
    }

    @WorkerThread
    fun deleteTheme(themeEntity: ThemeEntity) {
        themeDao.delete(themeEntity)
    }

    fun getTheme(id: Int) : LiveData<ThemeEntity> {
        return themeDao.getTheme(id)
    }

    fun getThemeIdOnTitle(title: String) : LiveData<Int> {
        return themeDao.getThemeIdOnTitle(title)
    }


}