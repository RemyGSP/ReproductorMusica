package com.guillem.reproductormusica

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewpeliculas.SongAdapter
import com.guillem.reproductormusica.databinding.ActivityMainBinding
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_main)
        var songList = listOf<Song>();
        var afterlife : Song = Song("Afterlife", "R.raw.stim_afterlife", "drawable");
        songList += afterlife;
        var songAdapter : SongAdapter = SongAdapter(songList);
        var mediaPlayer = MediaPlayer.create(view.context, R.raw.stim_afterlife)
        mediaPlayer.setVolume(1f,1f);
        mediaPlayer.start();


    }
}