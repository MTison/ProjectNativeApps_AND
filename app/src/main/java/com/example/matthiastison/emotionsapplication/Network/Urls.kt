package com.example.matthiastison.emotionsapplication.Network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Urls(@Json(name = "raw") val raw: String = "",
        @Json(name = "full") val full: String = "",
        @Json(name = "regular") val regular: String = "",
        @Json(name = "small") val small: String = "",
        @Json(name = "thumb") val thumb: String = ""
) : Parcelable {}