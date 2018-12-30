package com.example.matthiastison.emotionsapplication.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthiastison.emotionsapplication.Models.ThemeItem
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_subjects.*

class Subjects_Activity: AppCompatActivity() {

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        val item = intent.getSerializableExtra("THEME_ITEM") as ThemeItem

        Subjects_toolbar.setTitle(item.title)
        setSupportActionBar(Subjects_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


}