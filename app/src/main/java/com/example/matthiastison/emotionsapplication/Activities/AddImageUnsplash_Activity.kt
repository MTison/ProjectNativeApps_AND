package com.example.matthiastison.emotionsapplication.Activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.Fragments.ImageDownload_Fragment
import com.example.matthiastison.emotionsapplication.Network.Unsplash
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.SubjectViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_addimageunsplash.*
import kotlinx.android.synthetic.main.activity_subjectedit.*
import java.util.*

class AddImageUnsplash_Activity : AppCompatActivity() {

    private lateinit var item : Unsplash
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var theme: ThemeEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addimageunsplash)

        item = intent.getParcelableExtra("UNSPLASH_ITEM")
        theme = intent.getParcelableExtra("THEME_ITEM")

        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)

        Picasso.with(this).load(item.urls!!.regular).fit().into(imgView_UnsplashImage)
        edtView_SubjectDescription.setText(item.description)
    }

    override fun onStart() {
        super.onStart()

        btn_Save.setOnClickListener {
            saveSubject()
        }

        btn_Cancel.setOnClickListener {
            finish()
        }
    }

    private fun saveSubject() {
        val subjectTitle = edtView_SubjectTitle.text.toString()
        val subjectDate = edtView_SubjectDate.text.toString()
        val subjectDescription = edtView_SubjectDescription.text.toString()
        val imageRes = item.urls!!.regular

        if (subjectTitle.trim().isEmpty() || subjectDate.trim().isEmpty() ||
                subjectDescription.trim().isEmpty()) {
            Toast.makeText(this,"please provide all fields",Toast.LENGTH_LONG).show()
            return
        }

        subjectViewModel.insert(SubjectEntity(subjectTitle,subjectDate,subjectDescription,
                imageRes, 0 , theme.id, UUID.randomUUID().toString()))
        finish()
    }
}
