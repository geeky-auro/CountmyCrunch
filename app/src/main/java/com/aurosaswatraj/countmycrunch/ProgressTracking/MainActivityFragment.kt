package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.fragment_main_activity.*
import java.lang.RuntimeException



private const val TAG = "MainActivityFragment"

//    Create a ViewModel instance..
//    Describe viewModel. Remember to subscribe to it in onCreate.
/**
 * A simple [Fragment] subclass.
 * Use the [MainActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainActivityFragment : Fragment(),CursorRecyclerViewAdapter.onTaskClickListener {


    private val viewModel by lazy {
        // ViewModelProviders.of(activity!!).get(TaskTimerViewModel::class.java)
        ViewModelProvider(this).get(TrackViewModel::class.java)
    }

    private val mAdapter = CursorRecyclerViewAdapter(null,this)


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: called")
        super.onCreate(savedInstanceState)
        //            to provide the new cursor, when we observe that it has changed.
//            When the cursor changes, we pass the new one to swapCursor,
//            causing the adapter to get the new data.
        viewModel.cursor.observe(this) { cursor -> mAdapter.swapCursoe(cursor)?.close() }
    }


    override fun onAttach(context: Context) {

        Log.d(TAG, "onAttach: called")
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: called")
        task_list.layoutManager = LinearLayoutManager(context) // <-- set up RecyclerView
//        Setting up space between each tasks
        val itemDecorator=SpacingItemDecorator(10)
        task_list.addItemDecoration(itemDecorator)
        task_list.adapter = mAdapter

        super.onViewCreated(view, savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_activity, container, false)
    }


    override fun onEditClick(task: Track) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClick(task: Track) {
        viewModel.deleteTask(task.id)
        /**The onDeleteClick function in MainActivityFragment will then call the ViewModel's delete task function,
        to tell the ViewModel to delete the task.**/
    }
}