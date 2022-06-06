package com.example.livbus


import android.widget.MediaController
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var videoView : VideoView = findViewById(R.id.videoView)
        val mediaController : MediaController
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setVideoPath("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4")
        videoView.start()
        videoView.setMediaController(mediaController)
    }


}

