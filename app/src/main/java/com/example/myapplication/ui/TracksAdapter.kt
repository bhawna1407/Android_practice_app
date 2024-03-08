package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Album
import com.example.myapplication.model.Track

class TracksAdapter : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    private var tracks: List<Track> = emptyList()

    // Update the constructor to accept a list of albums
    fun setData(tracks: List<Track>) {
        this.tracks = tracks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = tracks[position]
        holder.bind(track)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(track: Track) {
            Glide.with(itemView)
                .load(track.images[0].url) // Use the URL of the album image
                .into(itemView.findViewById<ImageView>(R.id.albumImageView))
        }
    }
}
