package com.example.matthiastison.emotionsapplication.Models

import java.io.Serializable

data class SubjectItem(val id: Int,
                        override val title: String = "No title",
                        val date: String = "No date",
                        val imageRes: Int,
                        override val colorRes: Int)
    : Item(), Serializable {}