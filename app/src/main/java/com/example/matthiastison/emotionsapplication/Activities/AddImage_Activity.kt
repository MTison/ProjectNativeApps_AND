package com.example.matthiastison.emotionsapplication.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthiastison.emotionsapplication.Fragments.Chooser_Fragment
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_addimage.*

class AddImage_Activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addimage)

        AddImage_toolbar.title = "Foto toevoegen"
        setSupportActionBar(AddImage_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.AddImage_layout, Chooser_Fragment())
                .commit()
    }

}