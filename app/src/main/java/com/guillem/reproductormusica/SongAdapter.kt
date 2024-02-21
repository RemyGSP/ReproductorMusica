package com.example.recyclerviewpeliculas

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guillem.reproductormusica.R
import com.guillem.reproductormusica.Song
import java.io.Serializable

class SongAdapter(private val songList : List<Song>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

private lateinit var context : Context;
    private  var currentSong : Int = 0;
    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songName: TextView = itemView.findViewById(R.id.songName)
        var songImage: ImageView = itemView.findViewById(R.id.songImage)
        var playButton: Button = itemView.findViewById(R.id.Play)
        var stopButton : Button = itemView.findViewById(R.id.Stop)
        var restartButton : Button = itemView.findViewById(R.id.Restart)
        var durationBar : SeekBar = itemView.findViewById(R.id.seekBar)
        var durationText : TextView = itemView.findViewById(R.id.durationText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context);
        val filmView = inflater.inflate(R.layout.song_layout,parent,false);
        return ViewHolder(filmView)
    }

    override fun getItemCount(): Int {
        return songList!!.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song: Song = songList!!.get(position)
        Log.d("SongName",song.songName)
        val songName : TextView = holder.songName
        songName.text = song.songName
        holder.songImage.setImageResource(song.songImage)
        holder.songName.text = song.songName;
        var playbackPosition: Int = 0
        var durationBar = holder.durationBar
        val mediaPlayer = MediaPlayer.create(context, song.songPath)
        durationBar.max = mediaPlayer.duration
        val durationText = holder.durationText
        durationText.text = "0:00"
        mediaPlayer.setVolume(1f,1f)
        holder.playButton.setOnClickListener {
            mediaPlayer?.apply {
                if (!isPlaying) {
                    if (playbackPosition == 0) {
                        reset()
                        val songResource = song.songPath // Assuming songPath is the resource ID
                        setDataSource(context, Uri.parse("android.resource://${context.packageName}/$songResource"))
                        prepareAsync()
                        setOnPreparedListener { mp ->
                            mp.seekTo(playbackPosition)
                            mp.start()
                        }
                    } else {
                        val minutes = mediaPlayer.currentPosition / 1000 / 60
                        val seconds =  mediaPlayer.currentPosition / 1000 % 60
                        val formattedTime = String.format("%02d:%02d", minutes, seconds)
                        durationText.text = formattedTime
                        seekTo(playbackPosition)
                        start()
                    }
                }
            }
        }

        holder.stopButton.setOnClickListener {
            mediaPlayer?.apply {
                if (isPlaying) {
                    pause()
                    playbackPosition = currentPosition
                }
            }
        }

        holder.restartButton.setOnClickListener {
            mediaPlayer?.apply {
                seekTo(0)
                playbackPosition = 0;
                start()
            }
        }

        holder.durationBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mediaPlayer.seekTo(progress)
                playbackPosition = progress
                val minutes = playbackPosition / 1000 / 60
                val seconds = playbackPosition / 1000 % 60
                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                durationText.text = formattedTime
            }


            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

    }
}




