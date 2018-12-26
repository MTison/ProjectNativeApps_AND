package com.example.matthiastison.emotionsapplication.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.Start_Activity
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_start_.*

class Theme_Fragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_theme,container,false)

        return v;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // casting 'Start-activity' as the base activity in fragment
        activity as Start_Activity
    }

}