package com.example.matthiastison.emotionsapplication.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_start_.view.*

class Theme_Fragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_theme,container,false)

        activity?.Start_activityToolbar?.subtitle = "Themes"

        return v;
    }

}