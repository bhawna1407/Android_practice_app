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
interface TracksClickListener {
    fun onTrackClicked(trackId: String)
}

class TracksAdapter(private val listener: TracksClickListener) : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    private var tracks: List<Track> = emptyList()

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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener  {
        private lateinit var currentTrackId: String

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val trackId = tracks[position].id
                listener.onTrackClicked(trackId)
            }
        }
        fun bind(track: Track) {
            currentTrackId = track.id
            if (!track.images.isNullOrEmpty() && track.images[0].url != null) {
                Glide.with(itemView)
                    .load(track.images[0].url)
                    .into(itemView.findViewById(R.id.albumImageView))
            }else{
                Glide.with(itemView)
                    .load("https://i.guim.co.uk/img/media/0a2bdc778140a020a6b83f1b28213ed57f76be1e/0_0_5000_3000/master/5000.jpg?width=480&dpr=1&s=none")
                    .into(itemView.findViewById(R.id.albumImageView))
            }
        }
    }
}
