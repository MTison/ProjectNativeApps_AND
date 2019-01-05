package com.example.matthiastison.emotionsapplication.Database.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity

@Dao
interface ThemeDao {

    @Insert
    fun insert(theme: ThemeEntity)

    @Update
    fun update(theme: ThemeEntity)

    @Delete
    fun delete(theme: ThemeEntity)

    @Query("SELECT * FROM theme_table")
    // Live data makes it an observable, so activity gets notified when changes might occur
    fun getAllThemes() : LiveData<List<ThemeEntity>>

    @Query("SELECT * FROM theme_table WHERE id = :themeId")
    fun getTheme(themeId: Int) : LiveData<ThemeEntity>

    @Query("SELECT id FROM theme_table WHERE title = :themeTitle")
    fun getThemeIdOnTitle(themeTitle: String) : LiveData<String>

}