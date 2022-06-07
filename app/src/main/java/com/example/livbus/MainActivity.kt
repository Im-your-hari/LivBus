package com.example.livbus


import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.widget.MediaController
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import java.io.File
import java.lang.Exception


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
        videoView.setVideoPath("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")
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
            /*val request = DownloadManager.Request(Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
                .setTitle("Cartoon")
                .setDescription("Downloading..!")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(false)

            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

            dm.enqueue(request)
            */

            download("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","Bus")



        }

    }

    private fun download(url:String,fileName:String) {
        try {
            var downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var videoLink = Uri.parse(url)
            var request = DownloadManager.Request(videoLink)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType("video/mp4")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(fileName)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+fileName+".mp4")
            downloadManager.enqueue(request)

            Toast.makeText(this,"Downloading...",Toast.LENGTH_LONG).show()
        }catch (e:Exception){
            Toast.makeText(this,"Recording failed...",Toast.LENGTH_LONG).show()
        }
    }


}

