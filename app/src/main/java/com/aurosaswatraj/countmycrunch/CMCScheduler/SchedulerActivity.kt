package com.aurosaswatraj.countmycrunch.CMCScheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aurosaswatraj.countmycrunch.Dialogs.UserDarkModeDialog
import com.aurosaswatraj.countmycrunch.R
import com.aurosaswatraj.countmycrunch.databinding.ActivityMainBinding

class SchedulerActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val darkModeDialog= UserDarkModeDialog()
        darkModeDialog.darkModeDialog(this,this)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        createNotificationChannel()




    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name:CharSequence="CMCReminderChannel"
            val description="Channel for Alarm Manager"
            val importance=NotificationManager.IMPORTANCE_HIGH
            val channel=NotificationChannel("ID",name,importance)
            channel.description=description
            val notificationManager=getSystemService(
                NotificationManager::class.java
            )

            notificationManager.createNotificationChannel(channel)

        }
    }
}