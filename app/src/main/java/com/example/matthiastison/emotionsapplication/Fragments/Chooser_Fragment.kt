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

            val imageCaptureFragment = ImageCapture_Fragment()
            imageCaptureFragment.arguments = arguments

            activity.supportFragmentManager.
                    beginTransaction().
                    replace(R.id.AddImage_layout,imageCaptureFragment)
                    .addToBackStack(null)
                    .commit()
        }

        btn_ImageDownload.setOnClickListener {
            activity.supportActionBar?.title = "Foto toevoegen - Download"
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

            val imageDownloadFragment = ImageDownload_Fragment()
            imageDownloadFragment.arguments = arguments

            activity.supportFragmentManager.
                    beginTransaction().
                    replace(R.id.AddImage_layout,imageDownloadFragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // casting 'AddImage_Activity' as the base activity in fragment
        // when the activity is done initializing itself
        activity as AddImage_Activity
    }

    override fun onDestroy() {
        super.onDestroy()
        btn_ImageCapture.setOnClickListener(null)
        btn_ImageDownload.setOnClickListener(null)
    }

}