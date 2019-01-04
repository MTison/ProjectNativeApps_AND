package com.example.matthiastison.emotionsapplication.ViewModels


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.WorkerThread
import com.example.matthiastison.emotionsapplication.Database.EmotionsAppRepository
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity

// androidViewModel automatically passes down the application context, it is needed to instantiate our db instance
class SubjectViewModel(application: Application) : AndroidViewModel(application) {

    private val subjectRepository: EmotionsAppRepository = EmotionsAppRepository(application)

    private val allSubjects : LiveData<ArrayList<SubjectEntity>> = subjectRepository.getAllSubjects

    @WorkerThread
    fun insert(subjectEntity: SubjectEntity) {
        subjectRepository.insertSubject(subjectEntity)
    }

    @WorkerThread
    fun update(subjectEntity: SubjectEntity) {
        subjectRepository.updateSubject(subjectEntity)
    }

    @WorkerThread
    fun delete(subjectEntity: SubjectEntity) {
        subjectRepository.deleteSubject(subjectEntity)
    }

    fun getAllSubjects(): LiveData<ArrayList<SubjectEntity>> {
        return allSubjects
    }

    fun getSubject(id: String) : LiveData<SubjectEntity> {
        return subjectRepository.getSubject(id)
    }

    fun getSubjectsForTheme(themeid: Int) : LiveData<ArrayList<SubjectEntity>> {
        return subjectRepository.getSubjectsForTheme(themeid)
    }
}