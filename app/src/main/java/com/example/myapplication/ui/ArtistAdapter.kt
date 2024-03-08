import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.myapplication.R
import com.example.myapplication.model.Album
import com.example.myapplication.model.Artist
import com.example.myapplication.model.SeveralArtist
interface ArtistsClickListener {
    fun onArtistClicked(artistId: String)
}
class ArtistsAdapter(private val listener: ArtistsClickListener) : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    private var artists: List<SeveralArtist> = emptyList()

    fun setData(artists: List<SeveralArtist>) {
        this.artists = artists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = artists[position]
        holder.bind(artist)
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val imageView: ImageView = itemView.findViewById(R.id.albumImageView)
        private lateinit var currentArtistId: String

        init {
                itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val artistId = artists[position].id
                listener.onArtistClicked(artistId)
            }
        }

        fun bind(artist: SeveralArtist) {
            currentArtistId = artist.id
            Glide.with(itemView)
                .load(artist.images[0].url) // Use the URL of the album image
                .into(itemView.findViewById<ImageView>(R.id.albumImageView))
        }

    }
}
