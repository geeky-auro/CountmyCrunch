package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteQueryBuilder
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
        val match = uriMatcher.match(uri)
        //        matcher is used to decide what matcher has been passed.>!
        Log.d(TAG, "query match is $match")
//        use a query builder to build the query that will be executed by the database
        val queryBuilder = SQLiteQueryBuilder()
//        Copy paste the code..!
        when (match) {
            TRACKS -> queryBuilder.tables = TrackContract.TABLE_NAME // SELECT ______ FROM Tasks

            TRACKS_ID -> {
                queryBuilder.tables = TrackContract.TABLE_NAME // SELECT _____ FROM Tasks
                val taskId = TrackContract.getId(uri) // Long of our taskId. E.g. 1
                queryBuilder.appendWhere("${TrackContract.Columns.ID} = ") // SELECT ______ FROM Tasks WHERE (_id =)      // <-- change method
                queryBuilder.appendWhereEscapeString("$taskId") // SELECT ______ FROM Tasks WHERE (id = 'taskId')
            }
            //queryBuilder.appendWhereEscapeString() is used to append values not append entire where clause
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        val db=AppDatabase.getInstance(context!!).readableDatabase // Our database in "TaskTimer.db"

        val cursor =
            queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        Log.d(TAG, "query: rows in returned cursor = ${cursor.count}") // TODO remove this line

        return cursor
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

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d(TAG, "insert called with uri $uri")
//        The code for the matched node (added using addURI), or -1 if there is no matched node.
        val match = uriMatcher.match(uri)
        //        matcher is used to decide what matcher has been passed.>!
        Log.d(TAG, "insert match is $match")
        val recordId: Long
        val returnUri: Uri
        when(match){
            TRACKS->{
                val db =
                    AppDatabase.getInstance(context!!).writableDatabase // Our database in "TaskTimer.db"
                recordId = db.insert(TrackContract.TABLE_NAME, null, values)
                if (recordId != -1L) {
                    returnUri = TrackContract.buildUriFromId(recordId)
                } else {
                    throw SQLException("Failed to insert,Uri was $uri")
                }
            }
            else -> {
                throw java.lang.IllegalArgumentException("Unknown uri:$uri")
            }
        }
        if (recordId > 0) {
            // something was inserted
            Log.d(TAG, "insert: Setting notifyChange with $uri")
            context?.contentResolver?.notifyChange(uri, null)
        }

        Log.d(TAG, "Existing insert,returning $returnUri")
        return returnUri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d(TAG, "delete called with uri $uri")
        val match = uriMatcher.match(uri)
        //        matcher is used to decide what matcher has been passed.>!
        Log.d(TAG, "delete match is $match")

//        Database to count how many rows were updated?
        var count: Int
//        Database to know the selection criteria
        var selectionCriteria: String

        when (match) {
//            performing Update against the whole table..!
            TRACKS -> {
                val db = AppDatabase.getInstance(context!!).writableDatabase
                count = db.delete(TrackContract.TABLE_NAME, selection, selectionArgs)
            }
//            Performing the update on a single row..!
            TRACKS_ID -> {
                val db = AppDatabase.getInstance(context!!).writableDatabase
                val id = TrackContract.getId(uri)
                selectionCriteria = "${TrackContract.Columns.ID} = $id"

                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }

                count = db.delete(TrackContract.TABLE_NAME, selectionCriteria, selectionArgs)
            }

            else->{ throw IllegalArgumentException("Unknown URI:$uri")}
        }

        if (count > 0) {
            // something was deleted
            Log.d(TAG, "delete: Setting notifyChange with $uri")
            context?.contentResolver?.notifyChange(uri, null)
        }

        Log.d(TAG, "Exiting delete, returning $count")
        return count
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d(TAG, "update called with uri $uri")
        val match = uriMatcher.match(uri)
        //        matcher is used to decide what matcher has been passed.>!
        Log.d(TAG, "update match is $match")

//        Database to count how many rows were updated?
        var count: Int
//        Database to know the selection criteria
        var selectionCriteria: String

        when (match) {
//            performing Update against the whole table..!
            TRACKS -> {
                val db = AppDatabase.getInstance(context!!).writableDatabase
                count = db.update(TrackContract.TABLE_NAME, values, selection, selectionArgs)
            }
//            Performing the update on a single row..!
            TRACKS_ID -> {
                val db = AppDatabase.getInstance(context!!).writableDatabase
                val id = TrackContract.getId(uri)
                selectionCriteria = "${TrackContract.Columns.ID} = $id"

                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }

                count =
                    db.update(TrackContract.TABLE_NAME, values, selectionCriteria, selectionArgs)
            }

            else->{ throw IllegalArgumentException("Unknown URI:$uri")}
        }


        if (count > 0) {
//            Something was Updated
            Log.d(TAG, "Update :Setting notifyChange with $uri ")
//            call the ContextResolver to notify the change,
            context?.contentResolver?.notifyChange(uri, null)
        }

        Log.d(TAG, "Exiting update function returning count...! :$count")
        return count


    }

}



/*For Creating Content Provider: https://developer.android.com/guide/topics/providers/content-provider-creating */

/** A summary of the queryBuilder.query constructor
 * queryBuilder >> "SELECT ______ FROM Tasks WHERE (id = 'taskId')" //taskId is a Long
db >> reference to our database to query from
projection >> "col, col, col, ..." An Array of columns that should be included for each row retrieved
selection >> "WHERE col = value" selection specifies the criteria for selecting rows
selectionArgs >> replaces placeholders in the selection clause
groupBy >> "GROUP BY col, col, ..." combines data for rows with identical values in all included columns
having >> "HAVING condition" eg: 'COUNT(_id) <10'. Adds a condition to the GROUP BY results
sortOrder >> "ORDER BY col, col,..." Orders the rows by a their value in a column/columns

The output with constructor elements, and output in an example instance below from a match of TASKS_ID
----------------------------------------------------
SELECT   projection    FROM Tasks WHERE (_id = 'taskId') ORDER BY sortOrder
SELECT Name, SortOrder FROM Tasks WHERE (_id = '1')      ORDER BY SortOrder */

//Cursor Constructor..!

/** In the case of our cursor constructor:

val cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)

- db was created in our query(){...} code.

- (at this point) projection was coded into our cursor's contentResolver in MainActivity and passed in the query() constructor.

- selection is null and not used (instead added via our when(match) directly into queryBuilder).

- selectionArgs is null and not used.

- groupBy was hardcoded as null.

- having was hardcoded as null.

- (at this point) sortOrder was coded into our cursor's contentResolver in MainActivity and passed in the query() constructor.


This creates a new cursor, and is returned to create the cursor in MainActivity, and subsequently used.
 * */

/**Note:Selection doesn't include the WHERE keyword, it's a list of columns.  selectionArgs provides the values for those columns.  Together, the two are used to create the WHERE clause.*/