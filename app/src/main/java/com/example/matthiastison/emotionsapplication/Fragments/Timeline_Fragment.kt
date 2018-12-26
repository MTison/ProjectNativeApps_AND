package com.example.matthiastison.emotionsapplication.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.Start_Activity
import com.example.matthiastison.emotionsapplication.Adapters.EmotionsRecyclerAdapter_timeline
import com.example.matthiastison.emotionsapplication.Models.TimelineItem
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.fragment_timeline.*

class Timeline_Fragment: Fragment() {

    private lateinit var gridLayoutManager : GridLayoutManager
    private lateinit var adapter: EmotionsRecyclerAdapter_timeline

    private var timelineItemsList: ArrayList<TimelineItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createDummyData()

        gridLayoutManager = GridLayoutManager(activity, 2)
        Timeline_recyclerView.layoutManager = gridLayoutManager

        adapter = EmotionsRecyclerAdapter_timeline(timelineItemsList)
        Timeline_recyclerView.adapter = adapter
    }

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_timeline,container,false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // casting 'Start_Activity' as the base activity in fragment
        activity as Start_Activity
    }

    override fun onStart() {
        super.onStart()

        if (timelineItemsList.isEmpty()) {
            // action when there are no timelineItems to be shown
        }
    }

    private fun createDummyData() {
        val resources = activity!!.applicationContext.resources
        val images = resources.obtainTypedArray(R.array.images)

        for(i in 1..5) {
            timelineItemsList.add(TimelineItem(i,"title $i","date $i", images.getResourceId(0, 0)))
        }
    }

}