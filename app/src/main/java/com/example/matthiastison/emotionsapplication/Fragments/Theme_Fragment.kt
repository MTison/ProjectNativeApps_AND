package com.example.matthiastison.emotionsapplication.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Activities.Start_Activity
import com.example.matthiastison.emotionsapplication.Adapters.ThemesRecyclerAdapter
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.Models.Item
import com.example.matthiastison.emotionsapplication.Models.ThemeItem
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.ThemeViewModel
import kotlinx.android.synthetic.main.fragment_theme.*
import java.util.*
import kotlin.collections.ArrayList

class Theme_Fragment: Fragment() {

    private lateinit var gridLayoutManager : GridLayoutManager
    private lateinit var adapter: ThemesRecyclerAdapter
    private lateinit var themeViewModel: ThemeViewModel

    private var themeItemsList: ArrayList<Item> = ArrayList()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        //createDummyData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_theme,container,false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(activity, 2)
        Theme_recyclerView.layoutManager = gridLayoutManager

        adapter = ThemesRecyclerAdapter()
        Theme_recyclerView.adapter = adapter

        themeViewModel = ViewModelProviders.of(this).get(ThemeViewModel::class.java)
        themeViewModel.getAllThemes().observe(this, Observer<ArrayList<ThemeEntity>> {
            Toast.makeText(activity,"changed", Toast.LENGTH_LONG).show()
            adapter.setItems(it!!)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // casting 'Start_Activity' as the base activity in fragment
        // when the activity is done initializing itself
        activity as Start_Activity
    }


    // TODO: get data from db instead of making dummy
    private fun createDummyData() {
        var tempItem: ThemeItem
        var themeNamesList = Arrays.asList("WONEN","RELATIES","VRIJE TIJD","LEVEN")

        val resources = activity!!.applicationContext.resources
        val typedColorArray = resources.obtainTypedArray(R.array.colors)

        for(i in 0..3) {
            tempItem = ThemeItem(i,themeNamesList[i], typedColorArray.getResourceId(i, 0))
            themeItemsList.add(tempItem)
        }
        // make data ready for GC so it doesn't stay bound to "typedImageArray"
        typedColorArray.recycle()
    }
}