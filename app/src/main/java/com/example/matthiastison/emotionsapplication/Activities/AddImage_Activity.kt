package com.example.matthiastison.emotionsapplication.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.Fragments.Chooser_Fragment
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_addimage.*

class AddImage_Activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addimage)

        // create the fragment and add a bundle to the argument, to pass the current theme
        val chooserFragment = Chooser_Fragment()
        val themeExtra = Bundle()
        val theme = intent.getParcelableExtra<ThemeEntity>("THEME_ITEM")

        AddImage_toolbar.title = "Foto toevoegen"
        setSupportActionBar(AddImage_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // parcelize the current theme and add to the arguments
        themeExtra.putParcelable("THEME_ITEM", theme)
        chooserFragment.arguments = themeExtra

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.AddImage_layout, chooserFragment)
                .commit()
    }

}