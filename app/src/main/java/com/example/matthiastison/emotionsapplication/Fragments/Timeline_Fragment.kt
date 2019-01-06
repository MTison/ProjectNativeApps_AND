package com.example.matthiastison.emotionsapplication.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.Activities.Start_Activity
import com.example.matthiastison.emotionsapplication.Adapters.TimelineRecyclerAdapter
import com.example.matthiastison.emotionsapplication.Database.Entities.SubjectEntity
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

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                TODO("not implemented")
            }

            override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
                //Building alert asking user to delete subject from timeline
                val builder = AlertDialog.Builder(activity!!)

                builder.setTitle("Verwijderen")
                builder.setMessage("Onderwerp verwijderen van tijdlijn?")

                builder.setPositiveButton("Ja") { _, _ ->
                    val subject = adapter.getItemAt(holder.adapterPosition)
                    subject.onTimeline = 0
                    subjectViewModel.update(subject)
                }

                builder.setNegativeButton("Nee") { dialog, _ ->
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()

                dialog.setOnShowListener {
                    val txtColor = resources.getColor(R.color.colorPrimary)
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(txtColor)
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(txtColor)
                }

                dialog.show()
            }

        }).attachToRecyclerView(Timeline_recyclerView)
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