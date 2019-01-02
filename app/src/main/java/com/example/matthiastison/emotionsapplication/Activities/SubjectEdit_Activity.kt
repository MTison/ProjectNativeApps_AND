package com.example.matthiastison.emotionsapplication.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Models.SubjectItem
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.activity_subjectedit.*

class SubjectEdit_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjectedit)

        val item = intent.getSerializableExtra("SUBJECT_ITEM") as SubjectItem

        SubjectEdit_toolbar.title = "Edit '${item.title}'"
        setSupportActionBar(SubjectEdit_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        makeDetails(item)

        btn_SubjectAddTimeline.setOnClickListener {
            //TODO: add subject item to the timeline
        }
        btn_SubjectDelete.setOnClickListener {
            //TODO: delete subject item from db
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
                Toast.makeText(this,"subject was successfully saved",Toast.LENGTH_LONG).show()
                //TODO: update subject item and return to parent activity
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun makeDetails(item: SubjectItem) {
        edtView_SubjectEditTitle.setText(item.title)
        imgView_SubjectEditImage.setImageResource(item.imageRes)
        edtView_SubjectEditDate.setText(item.date)
        edtView_SubjectEditDescription.setText("Add description here")
    }

}