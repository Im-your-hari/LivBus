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

    //private lateinit var fDatabase : FirebaseDatabase
    private lateinit var databasef : DatabaseReference

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exitBtn : Button = findViewById(R.id.exitBtn)
        val recButton : Button = findViewById(R.id.recButton)
        val videoView : VideoView = findViewById(R.id.videoView)
        val stdId : TextView = findViewById(R.id.stdView)
        val mediaController : MediaController

        val studentName = intent.getStringExtra("stdName")
        val studentId = intent.getStringExtra("stdId")

        val fDatabase : FirebaseDatabase
        var dRef : DatabaseReference
        fDatabase = FirebaseDatabase.getInstance("https://livbus-ae064-default-rtdb.firebaseio.com/")




        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setVideoPath("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")
        //videoView.setVideoPath("gs://livbus-ae064.appspot.com/video (2).mp4")//Firebase video
        videoView.start()
        videoView.setMediaController(mediaController)





        exitBtn.setOnClickListener{
            //finish()


            readData(studentId)




        }

        recButton.setOnClickListener{

            download("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","Bus")
        }

    }

    private fun readData(studentId: String?) {
        databasef = FirebaseDatabase.getInstance().getReference("/")
        if (studentId != null) {
            databasef.child(studentId).get().addOnSuccessListener {
                if (it.exists()){
                    val stuid = it.child("student_id").value

                    Toast.makeText(this,"stuid : ${stuid.toString()}",Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this,"No student id found on bus",Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener{
                Log.d(TAG,"Enter student id")
            }


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

