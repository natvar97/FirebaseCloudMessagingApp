package com.indialone.firebasecloudmessagingapp

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.indialone.firebasecloudmessagingapp.Constants.CHANNEL_ID

class MyNotificationManager(private val context: Context) {

    val NOTIFICATION_ID = 222

    fun showNotification(from: String, notification: String, intent: Intent) {

        val pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val mNotification = builder.setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setContentTitle(from)
            .setContentText(notification)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_launcher_background
                )
            )
            .build()

        mNotification.flags = Notification.FLAG_AUTO_CANCEL

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(NOTIFICATION_ID, mNotification)

    }

}