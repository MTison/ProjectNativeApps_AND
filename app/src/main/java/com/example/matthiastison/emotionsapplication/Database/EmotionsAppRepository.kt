package com.example.matthiastison.emotionsapplication.Database

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.matthiastison.emotionsapplication.Database.DAO.SubjectDao
import com.example.matthiastison.emotionsapplication.Database.DAO.ThemeDao
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import org.jetbrains.anko.doAsync
import kotlin.collections.ArrayList

// class that serves as layer between the db and the viewModels, so they have no responsibilities to the db
class EmotionsAppRepository(private val subjectDao: SubjectDao, private val themeDao: ThemeDao) {

    val getAllSubjects : LiveData<ArrayList<SubjectEntity>> = subjectDao.getAllSubjects() as LiveData<ArrayList<SubjectEntity>>
    val getAllThemes : LiveData<ArrayList<ThemeEntity>> = themeDao.getAllThemes() as LiveData<ArrayList<ThemeEntity>>

    // SUBJECTS CRUD CALLS
    // ///////////////////////

    @WorkerThread // run DB call asynchronously when writing to DB, to avoid blocking main thread
    fun insertSubject(subjectEntity: SubjectEntity) {
        doAsync {
            subjectDao.insert(subjectEntity)
        }
    }

    @WorkerThread
    fun updateSubject(subjectEntity: SubjectEntity) {
        doAsync {
            subjectDao.update(subjectEntity)
        }
    }

    @WorkerThread
    fun deleteSubject(subjectEntity: SubjectEntity) {
        doAsync {
            subjectDao.delete(subjectEntity)
        }
    }

    fun getSubject(id: String) : LiveData<SubjectEntity> {
        return subjectDao.getSubject(id)
    }

    fun getSubjectsForTheme(themeid: String) : LiveData<ArrayList<SubjectEntity>> {
        return subjectDao.getSubjectsForTheme(themeid) as LiveData<ArrayList<SubjectEntity>>
    }

    fun getSubjectsOnTimeline() : LiveData<ArrayList<SubjectEntity>> {
        return subjectDao.getSubjectsOnTimeline() as LiveData<ArrayList<SubjectEntity>>
    }

    // THEMES CRUD CALLS
    // ///////////////////////

    @WorkerThread
    fun insertTheme(themeEntity: ThemeEntity) {
        doAsync {
            themeDao.insert(themeEntity)
        }
    }

    @WorkerThread
    fun updateTheme(themeEntity: ThemeEntity) {
        doAsync {
            themeDao.update(themeEntity)
        }
    }

    @WorkerThread
    fun deleteTheme(themeEntity: ThemeEntity) {
        doAsync {
            themeDao.delete(themeEntity)
        }
    }

    fun getTheme(id: Int) : LiveData<ThemeEntity> {
        return themeDao.getTheme(id)
    }

    fun getThemeIdOnTitle(title: String) : LiveData<String> {
        return themeDao.getThemeIdOnTitle(title)
    }


}