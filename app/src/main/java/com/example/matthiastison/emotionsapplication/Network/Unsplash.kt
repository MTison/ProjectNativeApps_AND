package com.example.matthiastison.emotionsapplication.Network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Unsplash(@Json(name = "id") val id: String = "",
        @Json(name = "created_at") val createdAt: String = "",
        @Json(name = "updated_at") val updatedAt: String = "",
        @Json(name = "width") val width: Long = 0L,
        @Json(name = "height") val height: Long = 0L,
        @Json(name = "description") val description: String? = "",
        @Json(name = "urls") val urls: Urls? = null
) : Parcelable

