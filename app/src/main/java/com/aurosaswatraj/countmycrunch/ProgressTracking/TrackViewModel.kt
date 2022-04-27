package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.app.Application
import android.content.ContentValues
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.concurrent.thread


/**
 * Instead of extending the ViewModel class we'll extend an Android view model.
Both classes work the same way.
AndroidViewModel is a subclass of ViewModel,
but it also has an application property.
That's going to be useful if we want to access the database,
because we'll need an application to get a ContentResolver.
 *
 *
 * */


private const val TAG = "TrackViewModel"

class TrackViewModel(application: Application): AndroidViewModel(application) {


    private val contentObserver = object : ContentObserver(Handler()) {
        override fun onChange(selfChange: Boolean, uri: Uri?) {
//When a change is notified, the onChange function will be called.
//We log the fact, then we call loadTasks to reload the data,
//and update the current cursor LiveData.
            Log.d(TAG, "contentObserver.onChange: called. uri is $uri")
            loadTask()
        }
    }

    /**
     * We want it to make a cursor available via a LiveData object.
     * When the cursor changes, observers will be notified of that change.
     * The observer will be MainActivityFragment.
     * When it gets notified of a change,
     * it'll swap the cursor in the RecyclerviewAdapter.
     *
     * */

    private val databaseCursor = MutableLiveData<Cursor>()

    //    Defining our cursor as a LiveData cursor
    val cursor: LiveData<Cursor>
        //    Return the database cursor in the get method.
        get() = databaseCursor

    //  call that loadTasks function.(:.: Should be stated/called after the variable declarations )



    init {
        Log.d(TAG, "TaskTimerViewModel: Created.")
//        Register the observer
        getApplication<Application>().contentResolver.registerContentObserver(
            TrackContract.CONTENT_URI,
            true, contentObserver
        )
        loadTask()
    }

    private fun loadTask() {
        val projection = arrayOf(
            TrackContract.Columns.ID,
            TrackContract.Columns.TRACK_GENDER,
            TrackContract.Columns.TRACK_AGE,
            TrackContract.Columns.TRACK_HEIGHT,
            TrackContract.Columns.TRACK_WEIGHT,
            TrackContract.Columns.TRACK_AMT_CONSUMED,
            TrackContract.Columns.TRACK_REQUIRED_CONSUMED,
            TrackContract.Columns.TRACK_DATE,
            TrackContract.Columns.TRACK_SORT_ORDER )

//        <order by>Tasks.sortOrder, Tasks.Name
        val sortOrder = TrackContract.Columns.TRACK_SORT_ORDER


//
//        thread {
////      Define our cursor using to projection array and the sort order.
//            val cursor = getApplication<Application>().contentResolver.query(
//                TasksContract.CONTENT_URI, projection, null, null,
//                sortOrder
//            )
////        setting the database cursor value.
//            databaseCursor.postValue(cursor)
//        }



        thread {
//      Define our cursor using to projection array and the sort order.
            val cursor = getApplication<Application>().contentResolver.query(
                TrackContract.CONTENT_URI, projection, null, null,
                sortOrder)
//        setting the database cursor value.
            databaseCursor.postValue(cursor)
        }

    }


    fun saveTask(task:Track):Track{
        //    Update the Database if at least one field has changed
//    There's no need to hit the database unless this has happened.. :)
//    TO save the value in the database we will use the content provider..
//    Note: This functions updates the database if we are editing the existing task

//        To store values some where we use contentvalues
        val values= ContentValues()

        if (task.age!=0 || task.Weight!=0 || task.height.isNotEmpty() || task.amt_Consumed.toDouble()!=0.0
            || task.required_Consumed.toDouble()!=0.0){
//            Dont save a task with no name
            values.put(TrackContract.Columns.TRACK_GENDER,task.gender)
            values.put(TrackContract.Columns.TRACK_AGE,task.age)
            values.put(TrackContract.Columns.TRACK_HEIGHT,task.height)
            values.put(TrackContract.Columns.TRACK_WEIGHT,task.Weight)
            values.put(TrackContract.Columns.TRACK_AMT_CONSUMED,task.amt_Consumed.toDouble())
            values.put(TrackContract.Columns.TRACK_REQUIRED_CONSUMED,task.required_Consumed.toDouble())
            values.put(TrackContract.Columns.TRACK_DATE,task.date)
            values.put(TrackContract.Columns.TRACK_SORT_ORDER,task.sortorder)//defaults to zero if empty

//            Now, if the task id is zero it means we have a new task.
            if (task.id==0L){
//                 Add a new task
                thread {
                    Log.d(TAG, "saveTask: adding new task")
                    val uri=getApplication<Application>().contentResolver?.insert(
                        TrackContract.CONTENT_URI,values)
//                     make sure that the uri is valid or not null
                    if (uri!=null){
//                        assign an id then,
                        task.id=TrackContract.getId(uri)
                        Log.d(TAG,"saveTask: new id is ${task.id}")
                    }
                }

            }else{
//                 Task do have an ID, so we are updating..
               thread{
                    Log.d(TAG,"saveTask: updating task")
                    getApplication<Application>().contentResolver?.update(
                        TrackContract.buildUriFromId(task.id),values,null,null)

                }
            }
        }
//        We are returning the task because The reason is, that it may now have an ID that it didn't have before.
        return task

    }


    fun deleteTask(taskId:Long) {

//        In Kotlin thread{function}- a thread can be instantiated and executed simply
//        More Info: https://www.baeldung.com/kotlin/threads-coroutines#kotlin-extension

        thread {

            getApplication<Application>().contentResolver?.delete(
                TrackContract.buildUriFromId(taskId), null, null)
            /** We'll pass in the ID of the task to delete, and call the ContentResolver's delete function to perform the deletion.
            Our ContentProvider will take care of deleting an individual task, if its ID is provided in the URI.
            The buildURIFromId function that we added to the TasksContract class,
            will return a URI with the ID appended.
            Because we're providing the ID, we don't need to use the last two parameters;
            the where clause and selection args.
            We just build up the URI from the ID of the task that's been passed to the function,
            then call the delete function of the Content resolver.
            The onDeleteClick function in MainActivityFragment will then call the ViewModel's delete task function,
            to tell the ViewModel to delete the task.*/
            /** We'll pass in the ID of the task to delete, and call the ContentResolver's delete function to perform the deletion.
            Our ContentProvider will take care of deleting an individual task, if its ID is provided in the URI.
            The buildURIFromId function that we added to the TasksContract class,
            will return a URI with the ID appended.
            Because we're providing the ID, we don't need to use the last two parameters;
            the where clause and selection args.
            We just build up the URI from the ID of the task that's been passed to the function,
            then call the delete function of the Content resolver.
            The onDeleteClick function in MainActivityFragment will then call the ViewModel's delete task function,
            to tell the ViewModel to delete the task.*/
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: called")
        getApplication<Application>().contentResolver.unregisterContentObserver(contentObserver)
    }
}