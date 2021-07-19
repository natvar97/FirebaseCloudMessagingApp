package com.indialone.firebasecloudmessagingapp

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private lateinit var notificationManager: MyNotificationManager

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("TAG", "From: " + remoteMessage.from)

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("TAG", "Message data payload: " + remoteMessage.data)
            if (true) {
//                scheduleJob()
            } else {
//                handleNow()
            }
        }

        if (remoteMessage.notification != null) {
            Log.d("TAG", "Message Notification Body: " + remoteMessage.notification!!.body)
        }

        notifyUser(remoteMessage.from!!, remoteMessage.notification!!.body!!)

    }

    fun notifyUser(from: String, notification: String) {
        notificationManager = MyNotificationManager(applicationContext)

        notificationManager.showNotification(from, notification, Intent(applicationContext, MainActivity::class.java))

    }

}