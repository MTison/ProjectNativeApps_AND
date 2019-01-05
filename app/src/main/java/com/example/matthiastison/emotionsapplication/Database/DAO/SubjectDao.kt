package com.example.matthiastison.emotionsapplication.Database.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity

@Dao
interface SubjectDao {

    @Insert
    fun insert(subject: SubjectEntity)

    @Update
    fun update(subject: SubjectEntity)

    @Delete
    fun delete(subject: SubjectEntity)

    @Query("SELECT * FROM subject_table")
    // Live data makes it an observable, so activity gets notified when changes might occur
    fun getAllSubjects() : LiveData<List<SubjectEntity>>

    @Query("SELECT * FROM subject_table WHERE id = :subjectId")
    fun getSubject(subjectId: String) : LiveData<SubjectEntity>

    @Transaction
    @Query("SELECT * FROM subject_table WHERE theme_id = :themeId AND onTimeline = 0")
    fun getSubjectsForTheme(themeId: String) : LiveData<List<SubjectEntity>>

    @Transaction
    @Query("SELECT * FROM subject_table WHERE onTimeline = 1 ORDER BY theme_id")
    fun getSubjectsOnTimeline() : LiveData<List<SubjectEntity>>

}