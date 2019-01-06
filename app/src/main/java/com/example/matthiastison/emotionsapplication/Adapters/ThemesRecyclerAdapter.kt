package com.example.matthiastison.emotionsapplication.Adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.Subjects_Activity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.R
import kotlinx.android.synthetic.main.theme_recyclerview_item.view.*

class ThemesRecyclerAdapter: RecyclerView.Adapter<ThemesRecyclerAdapter.ViewHolder>() {

    private var themeItems: ArrayList<ThemeEntity> = ArrayList()

    // inflate the view through the parent's inflater and pass it to the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        var inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.theme_recyclerview_item, parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = themeItems.size

    fun setItems(items : ArrayList<ThemeEntity>) {
        themeItems = items
        notifyDataSetChanged()
    }

    // bind each item to a viewHolder and recycle them throughout scrolling
    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.bindItem(themeItems[pos])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var view: View = itemView
        private var item: ThemeEntity? = null

        // on initialization set the onClickListener to the view
        init {
            view.setOnClickListener(this)
        }

        // adding the onClickListener by extending from it and implementing the 'onClick()' method
        override fun onClick(v: View?) {
            // take context of the current item's view and create intent to new activity
            val subjectsIntent = Intent(itemView.context, Subjects_Activity::class.java)
            subjectsIntent.putExtra(THEME_ITEM_KEY, item)
            itemView.context.startActivity(subjectsIntent)
        }

        fun bindItem(item: ThemeEntity) {
            // add attributes of themeItem to the fields in the itemView
            this.item = item
            view.txtView_ThemeTitle.text = item.title
            view.Theme_layout.setBackgroundResource(item.colorRes!!)
        }
    }

    companion object {
        private val THEME_ITEM_KEY = "THEME_ITEM"
    }
}