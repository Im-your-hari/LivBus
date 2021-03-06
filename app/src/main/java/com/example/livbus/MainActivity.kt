package com.example.livbus


import android.app.DownloadManager
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var database : FirebaseDatabase

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exitBtn : Button = findViewById(R.id.exitBtn)
        val recButton : Button = findViewById(R.id.recButton)
        val videoView : VideoView = findViewById(R.id.videoView)
        val stdId : TextView = findViewById(R.id.stdView)
        val mediaController : MediaController

        val fDatabase : FirebaseDatabase
        var dRef : DatabaseReference
        fDatabase = FirebaseDatabase.getInstance()




        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setVideoPath("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")
        videoView.start()
        videoView.setMediaController(mediaController)





        exitBtn.setOnClickListener{
            finish()
        }

        recButton.setOnClickListener{
            stdId.text = "PRP19CS029"
            download("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","Bus")
            //Data
            dRef = fDatabase.getReference().child("student-id")
            dRef.child("student_id").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    stdId.text = value
                    stdId.text = "Need to check database structure...And arrange it.."
                    Log.d(TAG, "Value is: $value")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })


            //Data
        }

    }



    private fun download(url:String,fileName:String) {
        try {
            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val videoLink = Uri.parse(url)
            val request = DownloadManager.Request(videoLink)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType("video/mp4")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(fileName)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+fileName+".mp4")
            downloadManager.enqueue(request)

            Toast.makeText(this,"Downloading...",Toast.LENGTH_LONG).show()
        }catch (e:Exception){
            Toast.makeText(this,"recording failed...",Toast.LENGTH_LONG).show()
        }
    }


}

