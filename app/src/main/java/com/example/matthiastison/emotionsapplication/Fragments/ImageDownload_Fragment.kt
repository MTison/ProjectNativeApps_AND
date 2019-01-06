package com.example.matthiastison.emotionsapplication.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthiastison.emotionsapplication.Adapters.UnsplashRecyclerAdapter
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import com.example.matthiastison.emotionsapplication.R
import com.example.matthiastison.emotionsapplication.ViewModels.UnsplashViewModel
import com.pawegio.kandroid.textWatcher
import kotlinx.android.synthetic.main.fragment_imagedownload.*

class ImageDownload_Fragment : Fragment() {

    private lateinit var gridLayoutManager : GridLayoutManager
    private lateinit var adapter: UnsplashRecyclerAdapter
    private lateinit var unsplashViewModel: UnsplashViewModel
    private lateinit var theme: ThemeEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unsplashViewModel = ViewModelProviders.of(activity!!).get(UnsplashViewModel::class.java)

        // get the current theme through the arguments given to the fragment
        theme = arguments!!.getParcelable("THEME_ITEM")
    }

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_imagedownload,container,false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(activity, 2)
        Unsplash_recyclerView.layoutManager = gridLayoutManager

        adapter = UnsplashRecyclerAdapter()
        Unsplash_recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        // query the search request to the server when the user is typing in the input field
        edtText_SearchQuery.textWatcher {
            afterTextChanged {
                unsplashViewModel.requestPhotosOnQuery(it.toString()).observe(activity!!, Observer { themes ->
                    themes?.let {
                        adapter.setItems(it)
                    }
                })
            }
        }

        adapter.setTheme(theme)
    }
}