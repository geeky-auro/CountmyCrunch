package com.aurosaswatraj.countmycrunch.CMCScheduler

import android.app.Notification
import android.app.Notification.EXTRA_COMPACT_ACTIONS
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.aurosaswatraj.countmycrunch.DashBoard.DashBoard
import com.aurosaswatraj.countmycrunch.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {

    val i=Intent(p0,DashBoard::class.java)
        p1!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent=PendingIntent.getActivity(p0,0,i,0)


        val bitmap=BitmapFactory.decodeResource(p0?.resources,R.mipmap.ic_launcher_round)

//

        val builder=NotificationCompat.Builder(p0!!, App.CHANNEL_1_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setLargeIcon(bitmap)
            .setContentTitle("CountMyCruch")
            .setContentText("Time to Count Calories !")
            .setColor(Color.RED)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(Notification.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)


        val notificationManager=NotificationManagerCompat.from(p0)
        notificationManager.notify(123,builder.build())


    }
}