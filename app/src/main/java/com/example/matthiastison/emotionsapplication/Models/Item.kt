package com.example.matthiastison.emotionsapplication.Models

import java.io.Serializable

abstract class Item : Serializable {
    abstract val title: String
    abstract val colorRes: Int
}