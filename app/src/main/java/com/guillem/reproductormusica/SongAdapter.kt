package com.example.recyclerviewpeliculas

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guillem.reproductormusica.R
import com.guillem.reproductormusica.Song
import java.io.Serializable

class SongAdapter(private val songList : List<Song>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    var allSongs: MutableList<Song> = songList.toMutableList()
    fun addData(newCards: List<Song>) {
        val startPosition = allSongs.size
        allSongs.addAll(newCards)
        notifyItemRangeInserted(startPosition, allSongs.size)
        notifyDataSetChanged()

    }



    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songName: TextView = itemView.findViewById(R.id.songName)
        var playButton: Button = itemView.findViewById(R.id.Play)
        var stopButton : Button = itemView.findViewById(R.id.Stop)
        var restartButton : Button = itemView.findViewById(R.id.Restart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context);
        val filmView = inflater.inflate(R.layout.song_layout,parent,false);
        return ViewHolder(filmView)
    }

    override fun getItemCount(): Int {
        return songList!!.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song: Song = songList!!.get(position)
        holder.songName.text = song.songName;

    }


}