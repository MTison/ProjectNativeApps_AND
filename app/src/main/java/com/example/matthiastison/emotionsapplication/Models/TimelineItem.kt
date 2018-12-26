package com.example.matthiastison.emotionsapplication.Models

import java.io.Serializable

data class TimelineItem(val id: Int,
                        val title: String = "No title",
                        val date: String = "No date",
                        val imageRes: Int)
    : Serializable {}