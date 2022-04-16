package com.murilofb.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.SeekBar
import android.os.Bundle
import android.widget.SeekBar.OnSeekBarChangeListener
import android.os.Handler
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var txtPassTime: TextView
    private lateinit var txtDuration: TextView
    private lateinit var seekDuration: SeekBar
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button
    private val mmSS = SimpleDateFormat("mm:ss")
    private var mediaPlayer = MediaPlayer()
    private var musicDuration: Long = 0
    private val handler = Handler()
    private var runnable: Runnable? = null
    private var changeSeek = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlay = findViewById(R.id.btn_play)
        btnPause = findViewById(R.id.btn_pause)
        btnStop = findViewById(R.id.btn_stop)
        seekDuration = findViewById(R.id.seek_vol)
        txtPassTime = findViewById(R.id.txtPassTime)
        txtDuration = findViewById(R.id.txtDuration)
        runnable = object : Runnable {
            override fun run() {
                //Set Progress on Seekbar
                if (changeSeek) {
                    seekDuration.progress =
                        TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.currentPosition.toLong())
                            .toInt()
                }
                handler.postDelayed(this, 500)
            }
        }
        //O Runnable muda a seekbar ao decorrer da musica e o valor do texto de duration é igual ao valor da seekbar
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.panic_dont_threathen)
        musicDuration = mediaPlayer.duration.toLong()
        txtDuration.text = mmSS.format(musicDuration)
        seekDuration.progress = 0
        seekDuration.max = TimeUnit.MILLISECONDS.toSeconds(musicDuration).toInt()
        seekDuration.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                txtPassTime.text = mmSS.format(TimeUnit.SECONDS.toMillis(seekBar.progress.toLong()))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                changeSeek = false
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mediaPlayer.seekTo(
                    TimeUnit.SECONDS.toMillis(seekBar.progress.toLong()).toInt()
                )
                changeSeek = true
            }
        })
        btnPlay.setOnClickListener { playMusic() }
        btnPause.setOnClickListener { pauseMusic() }
        btnStop.setOnClickListener { stopMusic() }
    }

    private fun playMusic() {
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.panic_dont_threathen)
        mediaPlayer.seekTo(
            TimeUnit.SECONDS.toMillis(seekDuration!!.progress.toLong()).toInt()
        )
        mediaPlayer.start()
        handler.postDelayed(runnable!!, 0)
    }

    private fun stopMusic() {
        mediaPlayer.stop()
        seekDuration!!.progress = 0
        handler.removeCallbacks(runnable!!)
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) mediaPlayer.pause()
        handler.removeCallbacks(runnable!!)
    }
}