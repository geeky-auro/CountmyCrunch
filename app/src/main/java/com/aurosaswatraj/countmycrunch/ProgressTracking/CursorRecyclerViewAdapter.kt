package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_list_item.view.*

private const val TAG = "CursorRecyclerViewAdapt"

class TaskViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer{
//    By using an interface We can notify the fragment/activity that a button is tapped
//    MainActivityFragment will implement the functions in the interface,
//    and we can be sure that it contains the functions that we're going to call.

    private val gender: TextView =containerView.findViewById(R.id.tli_gender)
    private val age: TextView =containerView.findViewById(R.id.tli_age)
    private val height: TextView =containerView.findViewById(R.id.tli_height)
    private val weight: TextView =containerView.findViewById(R.id.tli_weight)
    private val amt_consumed: TextView =containerView.findViewById(R.id.tli_amt_consumed)
    private val amt_required: TextView =containerView.findViewById(R.id.tli_required_consumed)
    private val date: TextView =containerView.findViewById(R.id.tli_date)
    private val delBtn:Button=containerView.findViewById(R.id.tli_delete_btn)

    fun bind(task:Track,listener :CursorRecyclerViewAdapter.onTaskClickListener){

//           we can pass a reference to it, to our ViewHolder.
//           The ViewHolder will then call the appropriate functions on the listener,
//           and it will do that in its onClick functions.

        gender.text=task.gender
        age.text=task.age.toString()
        height.text=task.height
        weight.text=task.Weight.toString()
        amt_consumed.text=task.amt_Consumed.toEngineeringString()
        amt_required.text=task.required_Consumed.toEngineeringString()
        date.text=task.date

        delBtn.setOnClickListener {
            listener.onDeleteClick(task)
        }

//We'll pass in the task, because ultimately we'll want to provide the task details
//        to whatever is listening for the buttons to be tapped.
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
            holder.itemView.tli_date.text=holder.itemView.tli_date.toString().plus("Null")

        }
        else{
//            THere is some data /records available.>!
            if (!cursor.moveToPosition(position)) {
                throw IllegalStateException("Couldn't move to the position $position")
            }
//            Create a task object from the data in the cursor
            val task=Track(
                cursor.getString(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_GENDER)),
                cursor.getInt(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_AGE)),
                cursor.getString(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_HEIGHT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_WEIGHT)),
                cursor.getString(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_AMT_CONSUMED)).toBigDecimal(),
                cursor.getString(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_REQUIRED_CONSUMED)).toBigDecimal(),
                cursor.getString(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_DATE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(TrackContract.Columns.TRACK_SORT_ORDER))
            )

            task.id = cursor.getLong(cursor.getColumnIndexOrThrow(TrackContract.Columns.ID))

            holder.bind(task,listener)
        }
    }

    override fun getItemCount(): Int {

        val cursor = cursor
        val count = if (cursor == null || cursor.count == 0) {
            1 //because we populate single viewholder with instructions
        } else {
            cursor.count
        }
        Log.d(TAG, "Returning $count")
        return count
    }


    /**
     *
     * Swap in a new cursor,returning the old cursor
     * The returned old cursor is *not* closed
     *
     * @param newcursor The new cursor to be used
     * @return Reurns the previously set Cursors, or null if there wasn't
     * one.
     * If the given new Cursor is the same instance as the previous set
     * Cursor, null is returned.
     * */

    fun swapCursoe(newCursor: Cursor?): Cursor? {
//        This function should be called whenever the cursor that the adapters using is changed
        if (newCursor === cursor) {
            return null
        }
        val numItems = itemCount
        val oldCursor = cursor
        cursor = newCursor
        if (newCursor != null) {
//            notify the observers about the new cursor
            notifyDataSetChanged()
        } else {
//            notify the observers about the lack of a data set
            notifyItemRangeRemoved(0, numItems)
        }
        return oldCursor
    }

}