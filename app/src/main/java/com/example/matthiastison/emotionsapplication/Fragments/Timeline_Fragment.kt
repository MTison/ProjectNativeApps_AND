package com.example.matthiastison.emotionsapplication.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Activities.Start_Activity
import com.example.matthiastison.emotionsapplication.Adapters.TimelineRecyclerAdapter
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.Models.Item
import com.example.matthiastison.emotionsapplication.Models.TimelineItem
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_timeline.*

class Timeline_Fragment: Fragment() {

    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var adapter: TimelineRecyclerAdapter
    private lateinit var subjectViewModel: SubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subjectViewModel = ViewModelProviders.of(activity!!).get(SubjectViewModel::class.java)
    }

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_timeline,container,false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(activity)
        Timeline_recyclerView.layoutManager = linearLayoutManager

        adapter = TimelineRecyclerAdapter()
        Timeline_recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // casting 'Start_Activity' as the base activity in fragment
        // when the activity is done initializing itself
        activity as Start_Activity
    }

    override fun onStart() {
        super.onStart()

        subjectViewModel.getSubjectsOnTimeline().observe(this, Observer { subjects ->
            subjects?.let {
                Toast.makeText(activity,"on changed", Toast.LENGTH_LONG)
                adapter.setItems(it)

                if(adapter.itemCount == 0) {
                    txtView_NoTimelineSubjects.visibility = View.VISIBLE
                } else {
                    txtView_NoTimelineSubjects.visibility = View.INVISIBLE
                }
            }
        })
    }
}