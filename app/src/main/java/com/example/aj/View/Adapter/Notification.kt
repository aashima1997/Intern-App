package com.example.aj.View.Adapter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.aj.R
import com.example.aj.View.Activity.NavDrawer

class Notification(base: Context) : ContextWrapper(base) {
    val MYCHANNEL_ID = "App Alert Notification ID"
    val MYCHANNEL_NAME = "App Alert Notification"
    private var manager: NotificationManager? = null
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel = NotificationChannel(MYCHANNEL_ID, MYCHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)
        channel.enableLights(true)
        getManager().createNotificationChannel(channel)
    }
    fun getManager(): NotificationManager {
        if (manager == null) manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }
    fun getNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, NavDrawer::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        return NotificationCompat.Builder(applicationContext, MYCHANNEL_ID)
            .setContentTitle("Alarm!")
            .setContentText("Its a Reminder")
            .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
            .setColor(Color.YELLOW)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }
}

