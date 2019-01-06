package com.example.matthiastison.emotionsapplication.Activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.Network.Unsplash
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.SubjectViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_addimageunsplash.*
import java.util.*

class AddImageUnsplash_Activity : AppCompatActivity() {

    private lateinit var item : Unsplash
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var theme: ThemeEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addimageunsplash)

        // get the unsplash item clicked and the theme, through the intent
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
            // simply finish the activity when canceled
            finish()
        }
    }

    private fun saveSubject() {
        // take all the fields from the editText fields to make a new subject
        val subjectTitle = edtView_SubjectTitle.text.toString()
        val subjectDate = edtView_SubjectDate.text.toString()
        val subjectDescription = edtView_SubjectDescription.text.toString()
        val imageRes = item.urls!!.regular

        if (subjectTitle.trim().isEmpty() || subjectDate.trim().isEmpty() ||
                subjectDescription.trim().isEmpty()) {
            Toast.makeText(this,"please provide all fields",Toast.LENGTH_LONG).show()
            return
        }

        // insert new made subject in the db
        subjectViewModel.insert(SubjectEntity(
                subjectTitle,subjectDate,
                subjectDescription,imageRes, 0 ,
                theme.id, UUID.randomUUID().toString()))

        Toast.makeText(this,"Item will be added!", Toast.LENGTH_SHORT).show()
        // creating handler to delay the finishing of the activity until after the toast, so the activity can finish afterwards
        Handler().postDelayed({
            this@AddImageUnsplash_Activity.finish()}, 1500)
    }

    override fun onDestroy() {
        super.onDestroy()
        btn_Save.setOnClickListener(null)
        btn_Cancel.setOnClickListener(null)
    }
}
