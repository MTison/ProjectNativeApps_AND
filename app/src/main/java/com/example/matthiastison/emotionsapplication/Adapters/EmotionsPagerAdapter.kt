package com.example.matthiastison.emotionsapplication.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class EmotionsPagerAdapter(fm: FragmentManager,private val fragmentList: ArrayList<Fragment>): FragmentPagerAdapter(fm) {

    override fun getItem(index: Int): Fragment {
        return fragmentList.get(index)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}