package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

object TrackContract {

    internal const val TABLE_NAME = "Tracks"

    //    Tasks fields
    /** The URI to ACCESS THE TASK TABLE*/
    const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    val CONTENT_URI: Uri = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)

    object Columns{

        const val ID=BaseColumns._ID
        const val TRACK_GENDER="Gender"
        const val TRACK_AGE="Age"
        const val TRACK_HEIGHT="Height"
        const val TRACK_AMT_CONSUMED="Amt_Consumed"
        const val TRACK_REQUIRED_CONSUMED="Required"
        const val TRACK_DATE="Date"
        const val TRACK_SORT_ORDER="SortOrder"


    }


    fun getId(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id: Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }




}