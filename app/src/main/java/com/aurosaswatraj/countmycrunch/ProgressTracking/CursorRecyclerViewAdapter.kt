package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.extensions.LayoutContainer


private const val TAG = "CursorRecyclerViewAdapt"

class TaskViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer{
//    By using an interface We can notify the fragment/activity that a button is tapped
//    MainActivityFragment will implement the functions in the interface,
//    and we can be sure that it contains the functions that we're going to call.

    fun bind(task:Track,listener :CursorRecyclerViewAdapter.onTaskClickListener){

         }
    }



class CursorRecyclerViewAdapter:RecyclerView.Adapter<TaskViewHolder>() {

    interface onTaskClickListener{
        fun onEditClick(task:Track)
        fun onDeleteClick(task:Track)
//      Now that we've defined the interface, we can pass in a reference to something that implements that interface,
//      so that the adapter knows what to call.(Adding in the primary Constructor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        Log.d(TAG, "onCreateViewHolder STARTS: new View Requested.!")
        val view =LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        return TaskViewHolder(view)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}