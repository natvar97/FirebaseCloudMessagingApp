package com.indialone.firebasecloudmessagingapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    //    private lateinit var tvToken : TextView
    private var myFirebaseInstanceIdService = MyFirebaseInstanceIdService()
    private lateinit var etEmail: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tvToken = findViewById(R.id.tv_token)
        myFirebaseInstanceIdService.retrieveCurrentToken(this)
//        tvToken.setText(getToken())

        etEmail = findViewById(R.id.et_email)
        btnRegister = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            sendTokenToServer()
        }

    }


    // not require
    private fun sendTokenToServer() {
        val email = etEmail.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter valid Email", Toast.LENGTH_SHORT).show()
        } else {
            if (getToken() != null) {
                val stringRequest = object : StringRequest(
                    Method.POST,
                    "",
                    Response.Listener {

                    },
                    Response.ErrorListener {

                    }
                ) {}
                val requestQueue = Volley.newRequestQueue(this)
                requestQueue.add(stringRequest)
            } else {
                Toast.makeText(this, "Token not generated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun storeToken(token: String?) {
        val sharedPrefs =
            getSharedPreferences(Constants.FIREBASE_SHAREDPREFS, Context.MODE_PRIVATE)

        val editor = sharedPrefs.edit()
        editor.putString(Constants.TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        val sharedPrefs =
            getSharedPreferences(Constants.FIREBASE_SHAREDPREFS, Context.MODE_PRIVATE)
        return sharedPrefs.getString(Constants.TOKEN, null)
    }

}