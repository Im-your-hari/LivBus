package com.example.livbus


import android.app.DownloadManager
import android.content.ContentValues
import android.net.Uri
import android.widget.MediaController
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var exitBtn : Button = findViewById(R.id.exitBtn)
        var recButton : Button = findViewById(R.id.recButton)
        var videoView : VideoView = findViewById(R.id.videoView)
        val mediaController : MediaController

        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setVideoPath("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4")
        videoView.start()
        videoView.setMediaController(mediaController)

        //println("Duration : "+mediaController.scrollBarFadeDuration)
        //Log.i("The Duration is :: ", mediaController.scrollBarFadeDuration.toString())
        /*URI PARSING METHOD
        videoView.setVideoURI(
            Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        )

        */

        exitBtn.setOnClickListener(){
            finish()
        }

        recButton.setOnClickListener(){
            /*val request = DownloadManager.Request(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
                .setTitle("Cartoon")
                .setDescription("Downloading..!")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)

            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

            dm.enqueue(request)
            */
            Toast.makeText(this,"Recording will be add shortly..",Toast.LENGTH_LONG).show()

        }

    }





}

