package com.example.matthiastison.emotionsapplication.Adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.TimelineDetail_Activity
import com.example.matthiastison.emotionsapplication.Models.TimelineItem
import com.example.matthiastison.emotionsapplication.R

class EmotionsRecyclerAdapter_timeline(private val timelineItems: ArrayList<TimelineItem>): RecyclerView.Adapter<TimelineHolder>() {

    // inflate the view through the parent's inflater and pass it to the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): TimelineHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.timeline_recyclerview_item, parent,false)
        return TimelineHolder(inflatedView)
    }

    override fun getItemCount() = timelineItems.size

    // bind each item to a viewHolder and recycle them throughout scrolling
    override fun onBindViewHolder(viewHolder: TimelineHolder, pos: Int) {
        val item = timelineItems[pos]
        viewHolder.bindTimelineItem(item)
    }

}

// extending from 'RecyclerView.ViewHolder()' so it can be used by the recyclerView
class TimelineHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var v: View = itemView
    private var timelineItem: TimelineItem? = null

    // initializing the onClickListener by extending from it and implementing the 'onClick()' method
    override fun onClick(v: View?) {
        // take context of the current item's view and create intent to new activity
        val context = itemView.context
        val showTimelineItemIntent = Intent(context, TimelineDetail_Activity::class.java)
        showTimelineItemIntent.putExtra(TIMELINE_ITEM_KEY, timelineItem)
        context.startActivity(showTimelineItemIntent)
    }

    fun bindTimelineItem(item: TimelineItem) {
        // add attributes of timelineItem to the fields in the itemView
    }

    companion object {
        private val TIMELINE_ITEM_KEY = "TIMELINE_ITEM"
    }

}