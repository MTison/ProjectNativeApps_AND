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

    var fragmentNames = ArrayList<String>()

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_)

        Start_activityToolbar.subtitle = "Timeline"
        setSupportActionBar(Start_activityToolbar)

        setupViewpager(Start_activityViewpager)
        Start_activityTabLayout.setupWithViewPager(Start_activityViewpager,true)

        setupTabIcons()

        Start_activityViewpager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(p0: Int) {
                when (fragmentNames[p0]) {
                    "Timeline" -> Start_activityToolbar?.subtitle = "Timeline"
                    "Themes" -> Start_activityToolbar?.subtitle = "Themes"
                }
            }
        })
    }

    fun setupViewpager(viewpager: ViewPager) {
        var fragments = ArrayList<Fragment>()

        fragments.add(Timeline_Fragment())
        fragmentNames.add("Timeline")
        fragments.add(Theme_Fragment())
        fragmentNames.add("Themes")

        viewpager.adapter = EmotionsPagerAdapter(supportFragmentManager,fragments)
    }

    fun setupTabIcons() {
        Start_activityTabLayout.getTabAt(0)!!.setIcon(R.drawable.baseline_timeline_24)
        Start_activityTabLayout.getTabAt(1)!!.setIcon(R.drawable.baseline_people_24)
    }
}
