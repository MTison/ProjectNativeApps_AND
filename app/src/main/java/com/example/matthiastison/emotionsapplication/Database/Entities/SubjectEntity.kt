package com.example.matthiastison.emotionsapplication.Database.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subject_table",
        indices = [(Index(value = ["id"], unique = true)),
            (Index(value = ["theme_id"], unique = false))
        ],
        foreignKeys = [ForeignKey(
                entity = ThemeEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("theme_id"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)
        ])
data class SubjectEntity(var title : String?, var date : String?, var description : String?, var imageRes: String?,
                         var onTimeline: Int?, var theme_id : String?, @PrimaryKey var id : String) : Parcelable