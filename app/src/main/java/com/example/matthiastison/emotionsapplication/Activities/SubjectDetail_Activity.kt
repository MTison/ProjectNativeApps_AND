package com.example.matthiastison.emotionsapplication.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthiastison.emotionsapplication.Models.TimelineItem
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_subjectdetail.*

class SubjectDetail_Activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjectdetail)

        TimelineDetail_toolbar.title = "Details"
        setSupportActionBar(TimelineDetail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val item = intent.getSerializableExtra("TIMELINE_ITEM") as TimelineItem
        makeDetails(item)
    }

    private fun makeDetails(item: TimelineItem) {
        txtView_SubjectDetailTitle.text = item.title
        imgView_SubjectDetailImage.setImageResource(item.imageRes)
        txtView_SubjectDetailDate.text = item.date
        txtView_SubjectDetailDescription.text = "Add description here"
    }
}