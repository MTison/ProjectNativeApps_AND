package com.example.matthiastison.emotionsapplication.Adapters

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.SubjectDetail_Activity
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.timeline_recyclerview_item.view.*

class TimelineRecyclerAdapter: RecyclerView.Adapter<TimelineRecyclerAdapter.ViewHolder>() {

    private var subjectItems: ArrayList<SubjectEntity> = ArrayList()

    // inflate the view through the parent's inflater and pass it to the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        // initializing local variable
        var inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.timeline_recyclerview_item, parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = subjectItems.size

    fun setItems(items : ArrayList<SubjectEntity>) {
        subjectItems = items
        notifyDataSetChanged()
    }

    fun getItemAt(pos: Int): SubjectEntity {
        return subjectItems[pos]
    }

    // bind each item to a viewHolder and recycle them throughout scrolling
    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.bindItem(subjectItems[pos])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var view: View = itemView
        private var item: SubjectEntity? = null

        // on initialization set the onClickListener to the view
        init {
            view.setOnClickListener(this)
        }

        // adding the onClickListener by extending from it and implementing the 'onClick()' method
        override fun onClick(v: View?) {
            // take context of the current item's view and create intent to new activity
            val timelineSubjectDetailIntent = Intent(itemView.context, SubjectDetail_Activity::class.java)
            timelineSubjectDetailIntent.putExtra(TIMELINE_ITEM_KEY, item)
            itemView.context.startActivity(timelineSubjectDetailIntent)
        }

        //TODO: make use of data binding instead
        fun bindItem(item: SubjectEntity) {
            // add attributes of subjectItem to the fields in the itemView
            this.item = item
            Picasso.with(view.context).load(Uri.parse(item.imageRes)).fit().into(view.imgView_TimelineSubjectImage)
            view.txtView_TimelineSubjectTitle.text = item.title
            view.txtView_TimelineSubjectDate.text = item.date

            when(item.theme_id){
                "1" -> view.TimelineSubject_layout.setBackgroundResource(R.color.themeColor1)
                "2" -> view.TimelineSubject_layout.setBackgroundResource(R.color.themeColor2)
                "3" -> view.TimelineSubject_layout.setBackgroundResource(R.color.themeColor3)
                "4" -> view.TimelineSubject_layout.setBackgroundResource(R.color.themeColor4)
            }
        }
    }

    //TODO: search meaning and use of companion objects
    companion object {
        private val TIMELINE_ITEM_KEY = "TIMELINE_ITEM"
    }
}