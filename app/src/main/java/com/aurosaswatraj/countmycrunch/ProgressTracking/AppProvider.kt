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
        TODO("Not yet implemented")
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
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