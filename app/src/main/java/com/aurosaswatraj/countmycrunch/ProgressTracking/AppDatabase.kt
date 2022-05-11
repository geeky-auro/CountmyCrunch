package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


private const val TAG = "AppDatabase"
private const val DATABASE_NAME = "Tracks.db"
private const val DATABASE_VERSION = 3

internal class AppDatabase private constructor(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
){
    init {
        Log.d(TAG, "AppDatabase: initializing")
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate: Starts")
        val sSQL = """CREATE TABLE ${TrackContract.TABLE_NAME} (
            ${TrackContract.Columns.ID} INTEGER PRIMARY KEY NOT NULL,
            ${TrackContract.Columns.TRACK_GENDER} TEXT NOT NULL,
            ${TrackContract.Columns.TRACK_AGE} INTEGER NOT NULL,
            ${TrackContract.Columns.TRACK_HEIGHT} TEXT NOT NULL,
            ${TrackContract.Columns.TRACK_WEIGHT} INTEGER NOT NULL,
            ${TrackContract.Columns.TRACK_AMT_CONSUMED} DOUBLE NOT NULL,
            ${TrackContract.Columns.TRACK_REQUIRED_CONSUMED} TEXT NOT NULL,
            ${TrackContract.Columns.TRACK_DATE} TEXT NOT NULL,
            ${TrackContract.Columns.TRACK_SORT_ORDER} INTEGER);""".replaceIndent(" ")
        Log.d(TAG, sSQL)
        db.execSQL(sSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade : Starts")
        when (oldVersion) {
            1 -> {
//            Upgrade logic from Version 1
            }
            else -> {
                throw IllegalStateException("onUpgrade() with unknown version:$newVersion")
            }
        }
    }

    //Next Step is to create a singleton
//    Singleton class only allows a single instance to be create
//    To make a class singleton mark the constructor as private


    companion object : SingletonHolder<AppDatabase, Context>(::AppDatabase)

    //    Instead of companion object using singleton in it we have  created a separate singleton class to deal with..!
//    companion object{
//        @Volatile
//        private var instance:AppDatabase?=null
//
//        fun getInstance(context:Context):AppDatabase= instance?: synchronized(this)
//        {
//                 instance ?:AppDatabase(context).also { instance=it }
//        }
//    }

//    Next Step,is to use a content provider!
}