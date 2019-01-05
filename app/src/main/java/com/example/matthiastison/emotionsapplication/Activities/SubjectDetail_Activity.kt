package com.example.matthiastison.emotionsapplication.Activities

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Models.TimelineItem
import com.example.matthiastison.emotionsapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_subjectdetail.*

class SubjectDetail_Activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjectdetail)

        TimelineDetail_toolbar.title = "Details"
        setSupportActionBar(TimelineDetail_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val item = intent.getParcelableExtra<SubjectEntity>("TIMELINE_ITEM")
        makeDetails(item)
    }

    private fun makeDetails(item: SubjectEntity) {
        txtView_SubjectDetailTitle.text = item.title
        Picasso.with(this).load(Uri.parse(item.imageRes)).fit().into(imgView_SubjectDetailImage)
        txtView_SubjectDetailDate.text = item.date
        txtView_SubjectDetailDescription.text = item.description
    }
}