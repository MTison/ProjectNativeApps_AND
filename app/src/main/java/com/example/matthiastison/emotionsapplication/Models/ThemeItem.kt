package com.example.matthiastison.emotionsapplication.Models

import java.io.Serializable

data class ThemeItem(val id: Int,
                        override val title: String = "No title",
                        override val colorRes: Int)
    : Item(), Serializable {}