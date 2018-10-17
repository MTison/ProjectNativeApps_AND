package com.example.matthiastison.emotionsapplication.Activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.matthiastison.emotionsapplication.Adapters.EmotionsPagerAdapter
import com.example.matthiastison.emotionsapplication.Fragments.Theme_Fragment
import com.example.matthiastison.emotionsapplication.Fragments.Timeline_Fragment
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_start_.*

class Start_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_)

        setSupportActionBar(Start_activityToolbar)
        setupViewpager(Start_activityViewpager)

        Start_activityTabLayout.setupWithViewPager(Start_activityViewpager,true)
    }

    fun setupViewpager(viewpager: ViewPager) {
        var fragments = ArrayList<Fragment>()
        fragments.add(Timeline_Fragment())
        fragments.add(Theme_Fragment())
        viewpager.adapter = EmotionsPagerAdapter(supportFragmentManager,fragments)
    }
}
