package com.aurosaswatraj.countmycrunch.CMCScheduler

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.aurosaswatraj.countmycrunch.DashBoard.DashBoard
import com.aurosaswatraj.countmycrunch.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {

    val i=Intent(p0,DashBoard::class.java)
        p1!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent=PendingIntent.getActivity(p0,0,i,0)

        val builder=NotificationCompat.Builder(p0!!, App.CHANNEL_1_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("CountMyCruch Scheduler")
            .setContentText("Time to Check your Calories")
            .setColor(Color.RED)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(Notification.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        val notificationManager=NotificationManagerCompat.from(p0)
        notificationManager.notify(123,builder.build())


    }
}