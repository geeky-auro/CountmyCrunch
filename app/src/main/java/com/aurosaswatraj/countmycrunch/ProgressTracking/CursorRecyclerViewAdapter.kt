package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer


class TaskViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer{
//    By using an interface We can notify the fragment/activity that a button is tapped
//    MainActivityFragment will implement the functions in the interface,
//    and we can be sure that it contains the functions that we're going to call.

    fun bind(task:Track,listener :CursorRecyclerViewAdapter.onTaskClickListener){

    }
    }



class CursorRecyclerViewAdapter {

    interface onTaskClickListener{
        fun onEditClick(task:Track)
        fun onDeleteClick(task:Track)
//      Now that we've defined the interface, we can pass in a reference to something that implements that interface,
//      so that the adapter knows what to call.(Adding in the primary Constructor)
    }

}