package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log


private const val TAG = "AppProvider"
const val CONTENT_AUTHORITY = "com.aurosaswatraj.countmycrunch.ProgressTracking.provider"

private const val TRACKS = 100
private const val TRACKS_ID = 101


val CONTENT_AUTHORITY_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")


class AppProvider:ContentProvider(){


    private val uriMatcher by lazy { buildUriMatcher() }

    private fun buildUriMatcher(): UriMatcher {
        Log.d(TAG, "buildURIMatcher starts")

//      to match the content URI
//      every time user access table under content provider

        val matcher = UriMatcher(UriMatcher.NO_MATCH)

//        to access whole table
//        e.g. content://com.aurosaswatraj.tasktimer.provider/Tasks
        matcher.addURI(CONTENT_AUTHORITY, TrackContract.TABLE_NAME, TRACKS)

//        to access a particular row
//        of the table
//        e.g. content://com.aurosaswatraj.tasktimer.provider/Tasks/8
        matcher.addURI(CONTENT_AUTHORITY, "${TrackContract.TABLE_NAME}/#", TRACKS_ID)
//

//
//
//        matcher.addURI(CONTENT_AUTHORITY,DurationsContract.TABLE_NAME, TASKS_DURATIONS)
//        matcher.addURI(CONTENT_AUTHORITY,"${DurationsContract.TABLE_NAME}/#", TASKS_DURATIONS_ID)
//

        return matcher
    }


    override fun onCreate(): Boolean {
        /**        We cannot create our database instance as it can only be accessed by getInstance method*/
        return true
    }




    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d(TAG, "query called with uri $uri")
    }

    override fun getType(uri: Uri): String? {
        //      getType Function is used returning MIME Types..!
        val match = uriMatcher.match(uri)

//        matcher is used to decide what matcher has been passed.>!
        return when (match) {
            TRACKS -> TrackContract.CONTENT_TYPE

            TRACKS_ID -> TrackContract.CONTENT_ITEM_TYPE

            else -> throw IllegalArgumentException("unknown Uri: $uri")
        }
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

}