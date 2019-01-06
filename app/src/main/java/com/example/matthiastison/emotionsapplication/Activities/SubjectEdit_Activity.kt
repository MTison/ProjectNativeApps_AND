package com.example.matthiastison.emotionsapplication.Activities

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
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

        // set the button to go up to the parent activity and add close icon to it
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        initFields(item)
    }

    override fun onStart() {
        super.onStart()

        btn_SubjectAddTimeline.setOnClickListener {
            // update the current subject in the db, through the viewModel
            item.onTimeline = 1
            subjectViewModel.update(item)

            Toast.makeText(this,"Item will be added!", Toast.LENGTH_SHORT).show()
            // creating handler to delay the finishing of the activity until after the toast, so the activity can finish afterwards
            Handler().postDelayed({
                this@SubjectEdit_Activity.finish()}, 1500)
        }

        btn_SubjectDelete.setOnClickListener {
            // delete subject form the db through the viewModel
            subjectViewModel.delete(item)

            Toast.makeText(this,"Item will be deleted!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                this@SubjectEdit_Activity.finish()}, 1500)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // remove the listeners, so no unused references are kept
        btn_SubjectDelete.setOnClickListener(null)
        btn_SubjectAddTimeline.setOnClickListener(null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.subject_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu item
        when (item.itemId) {
            R.id.action_SAVE -> {
                saveSubject()
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
        // get the new values to be set to the current subject from the editText fields
        val subjectTitle = edtView_SubjectEditTitle.text.toString()
        val subjectDate = edtView_SubjectEditDate.text.toString()
        val subjectDescription = edtView_SubjectEditDescription.text.toString()

        if (subjectTitle.trim().isEmpty() || subjectDate.trim().isEmpty() || subjectDescription.trim().isEmpty()) {
            Toast.makeText(this,"please fill in all fields",Toast.LENGTH_LONG).show()
            return
        }

        // change the current subject's attributes and update is in the db
        item.title = subjectTitle
        item.date = subjectDate
        item.description = subjectDescription
        subjectViewModel.update(item)

        Toast.makeText(this,"Item saved!", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            this@SubjectEdit_Activity.finish()}, 1500)
    }
}