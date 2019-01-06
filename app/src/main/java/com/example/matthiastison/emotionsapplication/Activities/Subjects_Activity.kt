package com.example.matthiastison.emotionsapplication.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Adapters.SubjectsRecyclerAdapter
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.SubjectViewModel
import kotlinx.android.synthetic.main.activity_subjects.*

class Subjects_Activity: AppCompatActivity() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: SubjectsRecyclerAdapter
    private lateinit var item : ThemeEntity
    private lateinit var subjectViewModel: SubjectViewModel

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        item = intent.getParcelableExtra("THEME_ITEM")
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)

        gridLayoutManager = GridLayoutManager(this, 2)
        Subjects_recyclerView.layoutManager = gridLayoutManager

        adapter = SubjectsRecyclerAdapter()
        Subjects_recyclerView.adapter = adapter

        Subjects_toolbar.title = item.title
        setSupportActionBar(Subjects_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        // get the subjects for the specific theme from the db and add them to the recyclerView
        subjectViewModel.getSubjectsForTheme(item.id).observe(this, Observer { subjects ->
            subjects?.let {
                Toast.makeText(this,"Loading subjects", Toast.LENGTH_SHORT).show()
                adapter.setItems(it)

                // when no items are found in the adapter show message
                if(adapter.itemCount == 0) {
                    txtView_NoSubjects.visibility = View.VISIBLE
                } else {
                    txtView_NoSubjects.visibility = View.INVISIBLE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.theme_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_addImage -> {
                val addImageIntent = Intent(this, AddImage_Activity::class.java)
                addImageIntent.putExtra("THEME_ITEM", this.item)
                this.startActivity(addImageIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}