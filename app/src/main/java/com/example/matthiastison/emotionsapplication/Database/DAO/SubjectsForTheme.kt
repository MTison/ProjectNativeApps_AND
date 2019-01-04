package com.example.matthiastison.emotionsapplication.Database.DAO

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity

class SubjectsForTheme(
        @Embedded
        var theme : ThemeEntity? = null,
        @Relation(parentColumn = "id", entityColumn = "theme_id", entity = SubjectEntity::class)
        var subjects : List<SubjectEntity> = mutableListOf()
) {}