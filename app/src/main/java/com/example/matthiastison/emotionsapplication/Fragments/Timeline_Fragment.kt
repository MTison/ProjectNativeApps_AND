package com.example.matthiastison.emotionsapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.Start_Activity
import com.example.matthiastison.emotionsapplication.Adapters.EmotionsRecyclerAdapter
import com.example.matthiastison.emotionsapplication.Models.Item
import com.example.matthiastison.emotionsapplication.Models.TimelineItem
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.fragment_timeline.*

class Timeline_Fragment: Fragment() {

    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var adapter: EmotionsRecyclerAdapter

    private var timelineItemsList: ArrayList<Item> = ArrayList()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        createDummyData();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        adapter = EmotionsRecyclerAdapter(timelineItemsList, "timeline")
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

        if (timelineItemsList.isEmpty()) {
            // action when there are no timelineItems to be shown
        }
    }

    // TODO: get data from db instead of making dummy
    private fun createDummyData() {
        var tempItem: TimelineItem

        val resources = activity!!.applicationContext.resources
        val typedImageArray = resources.obtainTypedArray(R.array.images)

        for(i in 0..5) {
            tempItem = TimelineItem(i,"Title $i","Date $i", typedImageArray.getResourceId(i, 0), R.color.colorPrimary)
            timelineItemsList.add(tempItem)
        }
        // make data ready for GC so it doesn't stay bound to "typedImageArray"
        typedImageArray.recycle();
    }

}