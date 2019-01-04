package com.example.matthiastison.emotionsapplication.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Adapters.SubjectsRecyclerAdapter
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Models.Item
import com.example.matthiastison.emotionsapplication.Models.SubjectItem
import com.example.matthiastison.emotionsapplication.Models.ThemeItem
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.SubjectViewModel
import kotlinx.android.synthetic.main.activity_subjects.*
import com.google.gson.Gson

class Subjects_Activity: AppCompatActivity() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: SubjectsRecyclerAdapter
    private lateinit var item : Item
    private lateinit var subjectViewModel: SubjectViewModel

    private lateinit var prefs : SharedPreferences
    private lateinit var prefsEditor : SharedPreferences.Editor

    private var subjectItemList: ArrayList<Item> = ArrayList()

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        //createDummyData()
        item = intent.getSerializableExtra("THEME_ITEM") as ThemeItem

        //initSharedPreferences()
        //setItem(prefs.getString(CURRENT_THEME, null))

        gridLayoutManager = GridLayoutManager(this, 2)
        Subjects_recyclerView.layoutManager = gridLayoutManager

        adapter = SubjectsRecyclerAdapter()
        Subjects_recyclerView.adapter = adapter

        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        subjectViewModel.getAllSubjects().observe(this, Observer<ArrayList<SubjectEntity>> {
            Toast.makeText(this,"on changed", Toast.LENGTH_LONG)
            adapter.setItems(it!!)
        })

        Subjects_toolbar.title = item.title
        setSupportActionBar(Subjects_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.theme_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // respond to the action bar's Up/Home button and clear the sharedPreferences,
                // so no duplicates are made after reCreating activity
                // prefsEditor.remove(CURRENT_THEME).commit()
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            R.id.action_addImage -> {
                val addImageIntent = Intent(this, AddImage_Activity::class.java)
                this.startActivity(addImageIntent)
            }
            R.id.action_addVideo -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    //TODO: when changing to landscape saving the state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(CURRENT_THEME, item)
    }
    //TODO: when reCreating activity restoring the state
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let {
            item = it.getSerializable(CURRENT_THEME) as ThemeItem
        }
    }

    private fun initSharedPreferences() {
        // initializing the sharedPrefs for the activity
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefsEditor = prefs.edit()
    }

    private fun setItem(currentTheme: String?) {
        when(currentTheme == null) {
            true -> {
                // get theme from intent when no current theme is specified and add it to the sharedPreferences
                item = intent.getSerializableExtra("THEME_ITEM") as ThemeItem
                val jsonItem = Gson().toJson(item)
                prefsEditor.putString(CURRENT_THEME, jsonItem).commit()
            }
            false -> {
                // when there was already a theme, get it from the sharedPreferences
                val prefsItem = Gson().fromJson<ThemeItem>(currentTheme, ThemeItem::class.java!!)
                item = prefsItem
            }
        }
    }

    // TODO: get data from db instead of making dummy
    private fun createDummyData() {
        var tempItem: SubjectItem

        val resources = applicationContext.resources
        val typedImageArray = resources.obtainTypedArray(R.array.images)
        val typedColorArray = resources.obtainTypedArray(R.array.colors)

        for(i in 0..3) {
            tempItem = SubjectItem(i,"Title $i","Date $i",typedImageArray.getResourceId(i, 0),typedColorArray.getResourceId(i, 0))
            subjectItemList.add(tempItem)
        }
        // make data ready for GC so it doesn't stay bound to "typedImageArray"
        typedImageArray.recycle()
        typedColorArray.recycle()
    }

    companion object {
        private var CURRENT_THEME = "currentTheme"
        private var PREFS_NAME = "sharedPreferences"
    }
}