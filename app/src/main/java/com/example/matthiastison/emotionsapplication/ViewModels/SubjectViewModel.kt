package com.example.matthiastison.emotionsapplication.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.WorkerThread
import com.example.matthiastison.emotionsapplication.App
import com.example.matthiastison.emotionsapplication.Database.EmotionsAppRepository
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import javax.inject.Inject

class SubjectViewModel : ViewModel() {

    @Inject
    lateinit var subjectRepository: EmotionsAppRepository

    init {
        App.component.inject(this)
    }

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

    fun getSubjectsForTheme(themeid: String) : LiveData<ArrayList<SubjectEntity>> {
        return subjectRepository.getSubjectsForTheme(themeid)
    }

    fun getSubjectsOnTimeline() : LiveData<ArrayList<SubjectEntity>> {
        return subjectRepository.getSubjectsOnTimeline()
    }

}