import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Album
interface AlbumsClickListener {
    fun onAlbumClicked(albumId: String)
}
class AlbumsAdapter(private val listener: AlbumsClickListener) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private var albums: List<Album> = emptyList()

    // Update the constructor to accept a list of albums
    fun setData(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private lateinit var currentAlbumId: String
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val albumId = albums[position].id
                listener.onAlbumClicked(albumId)
            }
        }
        fun bind(album: Album) {
            currentAlbumId = album.id
            Glide.with(itemView)
                .load(album.images[0].url) // Use the URL of the album image
                .into(itemView.findViewById(R.id.albumImageView))
        }
    }
}
