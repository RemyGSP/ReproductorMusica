package com.guillem.reproductormusica

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewpeliculas.SongAdapter
import com.guillem.reproductormusica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var songAdapter : SongAdapter
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var songList = ArrayList<Song>()
        val afterlife = Song("Afterlife", R.raw.afterlife, R.drawable.afterlife)
        val secondsFromPanic = Song("10 Seconds from panic", R.raw.secondsrompanic10, R.drawable.seconds_from_panic)
        val mrBrightSide = Song("Mr.Brightside", R.raw.the_killers_mr_brightside, R.drawable.mrbrightside)
        songList.add(afterlife)
        songList.add(secondsFromPanic)
        songList.add(mrBrightSide)
        songAdapter= SongAdapter(songList)
        binding.songs.adapter = songAdapter
        binding.songs.layoutManager = GridLayoutManager(this, 1)
        Log.d("SongList", songList.toString()   )




    }
}