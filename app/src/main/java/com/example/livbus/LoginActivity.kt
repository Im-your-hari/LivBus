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
        var stdName : EditText = findViewById(R.id.stdName)
        var stdId : EditText = findViewById(R.id.stdId)
        loginBtn.setOnClickListener{
            Log.d(TAG, "Student Name => $stdName")
            /*var i = Intent(this,MainActivity::class.java)
            startActivity(i)*/
            //stdName = "Hari"
            Intent(this,MainActivity::class.java).also {
                it.putExtra("stdName",stdName.text.toString())
                it.putExtra("stdId",stdId.text.toString())
                startActivity(it)
            }

        }

    }


}

