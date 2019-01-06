package com.example.matthiastison.emotionsapplication.Database.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "theme_table",
        indices = [(Index(value = ["id"], unique = true))])
@Parcelize
data class ThemeEntity(var title : String?, var colorRes : Int?, @PrimaryKey var id: String) : Parcelable