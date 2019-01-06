package com.example.matthiastison.emotionsapplication.Adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Activities.AddImageUnsplash_Activity
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.Network.Unsplash
import com.example.matthiastison.emotionsapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.unsplash_recyclerview_item.view.*

class UnsplashRecyclerAdapter : RecyclerView.Adapter<UnsplashRecyclerAdapter.ViewHolder>() {

    private var unsplashItems: ArrayList<Unsplash> = ArrayList()
    private lateinit var theme: ThemeEntity

    // inflate the view through the parent's inflater and pass it to the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        // initializing local variable
        var inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.unsplash_recyclerview_item, parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = unsplashItems.size

    fun setItems(items : ArrayList<Unsplash>) {
        unsplashItems = items
        notifyDataSetChanged()
    }

    fun setTheme(theme: ThemeEntity) {
        this.theme = theme
    }

    // bind each item to a viewHolder and recycle them throughout scrolling
    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.bindItem(unsplashItems[pos])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var view: View = itemView
        private var item: Unsplash? = null

        // on initialization set the onClickListener to the view
        init {
            view.setOnClickListener(this)
        }

        // adding the onClickListener by extending from it and implementing the 'onClick()' method
        override fun onClick(v: View?) {
            // take context of the current item's view and create intent to new activity
            val addUnsplashIntent = Intent(itemView.context, AddImageUnsplash_Activity::class.java)
            addUnsplashIntent.putExtra(UNSPLASH_ITEM_KEY, item)
            addUnsplashIntent.putExtra(THEME_ITEM_KEY, theme)
            itemView.context.startActivity(addUnsplashIntent)
        }

        //TODO: make use of data binding instead
        fun bindItem(item: Unsplash) {
            // add attributes of unsplashItem to the fields in the itemView
            this.item = item
            Picasso.with(view.context).load(item.urls!!.regular).fit().into(view.imgView_UnsplashImage)
        }
    }

    //TODO: search meaning and use of companion objects
    companion object {
        private val THEME_ITEM_KEY = "THEME_ITEM"
        private val UNSPLASH_ITEM_KEY = "UNSPLASH_ITEM"
    }
}