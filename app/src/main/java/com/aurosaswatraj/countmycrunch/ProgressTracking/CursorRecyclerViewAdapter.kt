package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.R.layout
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_list_item.*
import kotlinx.android.synthetic.main.task_list_item.view.*

private const val TAG = "CursorRecyclerViewAdapt"

class TaskViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer{
//    By using an interface We can notify the fragment/activity that a button is tapped
//    MainActivityFragment will implement the functions in the interface,
//    and we can be sure that it contains the functions that we're going to call.

    fun bind(task:Track,listener :CursorRecyclerViewAdapter.onTaskClickListener){




         }
    }



class CursorRecyclerViewAdapter(private var cursor: Cursor?, private val listener:onTaskClickListener):RecyclerView.Adapter<TaskViewHolder>() {

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
        Log.d(TAG, "onBindViewHolder starts.!")
        val cursor = cursor //avoid smartcasts

        if (cursor == null || cursor.count == 0) {
            Log.d(TAG, "onBindViewHolder:providing instructions")
            holder.itemView.tli_gender.text=holder.itemView.tli_gender.toString().plus("Null")
            holder.itemView.tli_age.text=holder.itemView.tli_age.toString().plus("Null")
            holder.itemView.tli_height.text=holder.itemView.tli_height.toString().plus("Null")
            holder.itemView.tli_weight.text=holder.itemView.tli_weight.toString().plus("Null")
            holder.itemView.tli_amt_consumed.text=holder.itemView.tli_amt_consumed.toString().plus("Null")
            holder.itemView.tli_required_consumed.text=holder.itemView.tli_required_consumed.toString().plus("Null")
            holder.itemView.tli_required_consumed.text=holder.itemView.tli_required_consumed.toString().plus("Null")

        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}