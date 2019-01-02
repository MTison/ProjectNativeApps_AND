package com.example.matthiastison.emotionsapplication.Adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.SubjectDetail_Activity
import com.example.matthiastison.emotionsapplication.Activities.SubjectEdit_Activity
import com.example.matthiastison.emotionsapplication.Activities.Subjects_Activity
import com.example.matthiastison.emotionsapplication.Models.Item
import com.example.matthiastison.emotionsapplication.Models.SubjectItem
import com.example.matthiastison.emotionsapplication.Models.ThemeItem
import com.example.matthiastison.emotionsapplication.Models.TimelineItem
import com.example.matthiastison.emotionsapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.subject_recyclerview_item.view.*
import kotlinx.android.synthetic.main.theme_recyclerview_item.view.*
import kotlinx.android.synthetic.main.timeline_recyclerview_item.view.*

class EmotionsRecyclerAdapter(private val items: ArrayList<Item>, private val target: String): RecyclerView.Adapter<ViewHolder>() {

    // inflate the view through the parent's inflater and pass it to the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        // initializing local variable
        var inflatedView = View(parent.context)

        when(target) {
            "timeline" -> inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.timeline_recyclerview_item, parent,false)
            "themes" -> inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.theme_recyclerview_item, parent,false)
            "subjects" -> inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.subject_recyclerview_item, parent,false)
        }

        return ViewHolder(inflatedView, target)
    }

    override fun getItemCount() = items.size

    // bind each item to a viewHolder and recycle them throughout scrolling
    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.bindItem(item)
    }

}

// extending from 'RecyclerView.ViewHolder()' so it can be used by the recyclerView
class ViewHolder(itemView: View, private val target: String) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var item: Item? = null

    // on initialization set the onClickListener to the view
    init {
        view.setOnClickListener(this)
    }

    // adding the onClickListener by extending from it and implementing the 'onClick()' method
    override fun onClick(v: View?) {
        // take context of the current item's view and create intent to new activity
        val context = itemView.context

        when(target) {
            "timeline" -> {
                val timelineSubjectDetailIntent = Intent(context, SubjectDetail_Activity::class.java)
                timelineSubjectDetailIntent.putExtra(TIMELINE_ITEM_KEY, item)
                context.startActivity(timelineSubjectDetailIntent)
            }
            "themes" -> {
                val subjectsIntent = Intent(context, Subjects_Activity::class.java)
                subjectsIntent.putExtra(THEME_ITEM_KEY, item)
                context.startActivity(subjectsIntent)
            }
            "subjects" -> {
                val subjectEditIntent = Intent(context, SubjectEdit_Activity::class.java)
                subjectEditIntent.putExtra(SUBJECT_ITEM_KEY, item)
                context.startActivity(subjectEditIntent)
            }
        }
    }

    //TODO: make use of data binding instead
    fun bindItem(item: Item) {
        if(item is TimelineItem) {
            // add attributes of timelineItem to the fields in the itemView
            this.item = item
            Picasso.with(view.context).load(item.imageRes).fit().into(view.imgView_TimelineSubjectImage)
            view.txtView_TimelineSubjectDate.text = item.date
            view.txtView_TimelineSubjectTitle.text = item.title
            view.TimelineSubject_layout.setBackgroundResource(item.colorRes)
        }
        if(item is ThemeItem) {
            // add attributes of themeItem to the fields in the itemView
            this.item = item
            view.txtView_ThemeTitle.text = item.title
            view.Theme_layout.setBackgroundResource(item.colorRes)
        }
        if(item is SubjectItem) {
            // add attributes of subjectItem to the fields in the itemView
            this.item = item
            Picasso.with(view.context).load(item.imageRes).fit().into(view.imgView_SubjectImage)
            view.txtView_SubjectDate.text = item.date
            view.Subject_layout.setBackgroundResource(item.colorRes)
        }
    }

    //TODO: search meaning and use of companion objects
    companion object {
        private val TIMELINE_ITEM_KEY = "TIMELINE_ITEM"
        private val THEME_ITEM_KEY = "THEME_ITEM"
        private val SUBJECT_ITEM_KEY = "SUBJECT_ITEM"
    }

}