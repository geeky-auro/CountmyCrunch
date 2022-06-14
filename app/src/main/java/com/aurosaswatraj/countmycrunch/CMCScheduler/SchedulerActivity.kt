package com.aurosaswatraj.countmycrunch.CMCScheduler

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aurosaswatraj.countmycrunch.Dialogs.UserDarkModeDialog
import com.aurosaswatraj.countmycrunch.R

class SchedulerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheduler)
        val darkModeDialog= UserDarkModeDialog()
        darkModeDialog.darkModeDialog(this,this)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


    }
}