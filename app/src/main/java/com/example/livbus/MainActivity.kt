package com.example.livbus


import android.app.DownloadManager
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
import java.io.File


class MainActivity : AppCompatActivity() {

    //private lateinit var fDatabase : FirebaseDatabase
    private lateinit var databasef : DatabaseReference
    private lateinit var videoView : VideoView
    private lateinit var studentId : String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var exitBtn : Button = findViewById(R.id.exitBtn)
        var recButton : Button = findViewById(R.id.recButton)
        //var videoView : VideoView = findViewById(R.id.videoView)
        videoView = findViewById(R.id.videoView)
        var stdId : TextView = findViewById(R.id.stdView)
        var mediaController : MediaController
        var studentName = intent.getStringExtra("stdName")
        var studentId = intent.getStringExtra("stdId")

        var fDatabase : FirebaseDatabase
        var dRef : DatabaseReference
        fDatabase = FirebaseDatabase.getInstance("https://livbus-ae064-default-rtdb.firebaseio.com/")




        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        //videoView.setVideoPath("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")
        //videoView.setVideoPath("https://console.firebase.google.com/project/livbus-ae064/storage/livbus-ae064.appspot.com/files/video (2).mp4")//Firebase video
        videoView.setVideoPath("http://192.168.164.148:8000/video.avi")
        databasef = FirebaseDatabase.getInstance().getReference("/")

        stdId.text="$studentName : $studentId"
        //Loop for continous data check___________________________________________________________________________

         for(i in 0..45){

            if (i.toString()!==null){
                databasef.child(i.toString()).get().addOnSuccessListener {
                    if (it.exists()){
                        val stdId = it.child("student_id").value.toString()
                        if (studentId==stdId.replace("\\s+".toRegex(),"")){
                            //Function to play video....

                            videoView.start()
                            videoView.setMediaController(mediaController)
                            Toast.makeText(this,"$studentName is in the bus",Toast.LENGTH_LONG).show()
                            recButton.setOnClickListener{

                                download("http://192.168.164.148:8000/video.avi","Bus")
                            }
                        }
                    }else{
                        //Toast.makeText(this,"No student id found on bus",Toast.LENGTH_SHORT).show()

                    }
                }.addOnSuccessListener {
                    //Toast.makeText(this,"No student id found on bus",Toast.LENGTH_SHORT).show()
                }
            }


        }
        //____________________________________________________________________________________________________________________


        //video view start function______________________

        //videoView.start()
        //videoView.setMediaController(mediaController)
        //_______________________________________________



        //___________________________________________________________________________________________________________
        exitBtn.setOnClickListener{
            finish()
            //readData(studentId)

        }
        //___________________________________________________________________________________________________________

        /*/Record cheyyanulla program_________________________________________________________________________________
        recButton.setOnClickListener{

            download("http://192.168.164.148:8000/video.avi","Bus")
        }
        //____________________________________________________________________________________________________________
        */
    }


    //______________________________________________________________________________________________________________________



    //Download video to mobile_________________________________________________________________________________________________
    private fun download(url:String,fileName:String) {
        try {
            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val videoLink = Uri.parse(url)
            val request = DownloadManager.Request(videoLink)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType("video/x-msvideo")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(fileName)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+fileName+".avi")
            downloadManager.enqueue(request)

            Toast.makeText(this,"Downloading...",Toast.LENGTH_LONG).show()
        }catch (e:Exception){
            Toast.makeText(this,"recording failed...",Toast.LENGTH_LONG).show()
        }
    }
    //____________________________________________________________________________________________________________________________


}

