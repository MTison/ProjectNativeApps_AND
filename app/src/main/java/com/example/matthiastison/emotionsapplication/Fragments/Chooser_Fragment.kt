package com.example.matthiastison.emotionsapplication.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.AddImage_Activity
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.fragment_chooser.*
import android.support.v7.app.AppCompatActivity

class Chooser_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_chooser,container,false)
        val activity = activity as AppCompatActivity

        activity.supportActionBar?.title = "Foto toevoegen"
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as AppCompatActivity

        btn_ImageCapture.setOnClickListener {
            activity.supportActionBar?.title = "Foto toevoegen - Capture"
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

            activity.supportFragmentManager.
                    beginTransaction().
                    replace(R.id.AddImage_layout,ImageCapture_Fragment())
                    .addToBackStack(null)
                    .commit()
        }

        btn_ImageDownload.setOnClickListener {
            //TODO: download image through splashphoto API
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // casting 'AddImage_Activity' as the base activity in fragment
        // when the activity is done initializing itself
        activity as AddImage_Activity
    }

}