package com.example.matthiastison.emotionsapplication.Adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.SubjectEdit_Activity
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.subject_recyclerview_item.view.*

class SubjectsRecyclerAdapter : RecyclerView.Adapter<SubjectsRecyclerAdapter.ViewHolder>() {

    private var subjectItems: ArrayList<SubjectEntity> = ArrayList()

    // inflate the view through the parent's inflater and pass it to the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        // initializing local variable
        var inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.subject_recyclerview_item, parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = subjectItems.size

    fun setItems(items : ArrayList<SubjectEntity>) {
        subjectItems = items
        notifyDataSetChanged()
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
            val subjectEditIntent = Intent(itemView.context, SubjectEdit_Activity::class.java)
            subjectEditIntent.putExtra(SUBJECT_ITEM_KEY, item)
            itemView.context.startActivity(subjectEditIntent)
        }

        //TODO: make use of data binding instead
        fun bindItem(item: SubjectEntity) {
            // add attributes of subjectItem to the fields in the itemView
            this.item = item
            // Picasso.with(view.context).load(item.imageRes).fit().into(view.imgView_SubjectImage)
            view.txtView_SubjectTitle.text = item.title
            view.txtView_SubjectDate.text = item.date
            // view.Subject_layout.setBackgroundResource(item.colorRes)
        }
    }

    //TODO: search meaning and use of companion objects
    companion object {
        private val SUBJECT_ITEM_KEY = "SUBJECT_ITEM"
    }
}