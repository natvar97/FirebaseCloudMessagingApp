package com.indialone.firebasecloudmessagingapp

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService


class MyFirebaseInstanceIdService : FirebaseMessagingService() {

//    val BROADCAST_STRING = "myFcpToken"

    override fun onNewToken(token: String) {
        Log.d("TAG", "Refreshed token: $token")

//        sendRegistrationToServer(token)


    }


    fun retrieveCurrentToken(activity: Activity) {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    val msg = "Token: $token"
                    Log.e("token", msg)
                    if (activity is MainActivity) {
                        activity.storeToken(msg)
                    }
//                    applicationContext.sendBroadcast(Intent(BROADCAST_STRING))

                }
            }

    }

}