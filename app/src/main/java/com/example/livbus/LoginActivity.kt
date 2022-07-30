package com.example.livbus

import android.app.DownloadManager
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.core.Tag
import java.io.File



class LoginActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginBtn = findViewById<Button>(R.id.loginBtn)
        val stdName = findViewById<EditText>(R.id.stdName).text.toString()
        val stdId = findViewById<EditText>(R.id.stdId).text.toString()
        loginBtn.setOnClickListener{
            Log.d(TAG, "Student Name => $stdName")
            var i = Intent(this,MainActivity::class.java)
            startActivity(i)

        }

    }


}

