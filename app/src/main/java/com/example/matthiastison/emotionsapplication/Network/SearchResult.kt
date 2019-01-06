package com.example.matthiastison.emotionsapplication.Network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(@Json(name = "total_pages") val total_pages: Int? = null,
        @Json(name = "results") var results: List<Unsplash>) : Parcelable