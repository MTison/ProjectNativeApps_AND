package com.example.matthiastison.emotionsapplication.Activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Models.SubjectItem
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.SubjectViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_subjectedit.*

class SubjectEdit_Activity : AppCompatActivity() {

    private lateinit var item: SubjectEntity
    private lateinit var subjectViewModel: SubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjectedit)

        item = intent.getParcelableExtra("SUBJECT_ITEM")
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)

        SubjectEdit_toolbar.title = "Edit - ${item.title}"
        setSupportActionBar(SubjectEdit_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        initFields(item)
    }

    override fun onStart() {
        super.onStart()

        btn_SubjectAddTimeline.setOnClickListener {
            //TODO: add subject item to the timeline
            item.onTimeline = 1
            subjectViewModel.update(item)

            val resultIntent = intent
            setResult(RESULT_ADDED_TO_TIMELINE, resultIntent)
            finish()
        }

        btn_SubjectDelete.setOnClickListener {
            //TODO: delete subject item from db
            subjectViewModel.delete(item)

            val resultIntent = intent
            setResult(RESULT_DELETED_SUBJECT, resultIntent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.subject_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_SAVE -> {
                saveSubject()
                //TODO: update subject item and return to parent activity
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFields(item: SubjectEntity) {
        edtView_SubjectEditTitle.setText(item.title)
        Picasso.with(this).load(Uri.parse(item.imageRes)).fit().into(imgView_SubjectEditImage)
        edtView_SubjectEditDate.setText(item.date)
        edtView_SubjectEditDescription.setText(item.description)
    }

    private fun saveSubject() {
        val subjectTitle = edtView_SubjectEditTitle.text.toString()
        val subjectDate = edtView_SubjectEditDate.text.toString()
        val subjectDescription = edtView_SubjectEditDescription.text.toString()

        if (subjectTitle.trim().isEmpty() || subjectDate.trim().isEmpty() || subjectDescription.trim().isEmpty()) {
            Toast.makeText(this,"please fill in all fields",Toast.LENGTH_LONG).show()
            return
        }

        val resultIntent = intent
        resultIntent.putExtra("EXTRA_EDITED_TITLE",subjectTitle)
        resultIntent.putExtra("EXTRA_EDITED_DATE",subjectDate)
        resultIntent.putExtra("EXTRA_EDITED_DESCRIPTION",subjectDescription)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        var RESULT_ADDED_TO_TIMELINE = 10
        var RESULT_DELETED_SUBJECT = 11
    }
}