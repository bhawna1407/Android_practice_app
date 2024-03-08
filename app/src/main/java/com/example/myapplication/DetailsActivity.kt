package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.model.Album
import com.example.myapplication.model.AlbumResponse
import com.example.myapplication.model.Artist
import com.example.myapplication.model.Track
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    private lateinit var albumImageView: ImageView
    private lateinit var albumNameTextView: TextView
    private lateinit var albumPopularityTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        albumImageView = findViewById(R.id.albumImageView)
        albumNameTextView = findViewById(R.id.albumNameTextView)
        albumPopularityTextView = findViewById(R.id.albumPopularityTextView)
        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val type = intent.getStringExtra("type")
        if (id != null) {
            val baseUrl = if (type == "album") {
                "https://api.spotify.com/v1/albums/"
            } else if (type == "artist") {

                "https://api.spotify.com/v1/artists/"
            } else {
                "https://api.spotify.com/v1/tracks/"
            }
            RetrofitClient.setBaseUrl(baseUrl)
            val authToken =
                "Bearer BQA2JPsXV21c5H88VsszBn-k1TQcDP5DEGZEl6a1aioDv2RbrfKpscBJgtIqf03Sn6LBnbNc-EG7F32HKDbFVzWdgIuQEFKTsGNrNG2LBY9blGk7Z1w"
            var call: Call<Any>? = null

            id.let {
                call = when (type) {
                    "album" -> RetrofitClient.instance.getAlbumDetails(
                        authToken,
                        it
                    ) as Call<Any>

                    "track" -> RetrofitClient.instance.getTrackDetails(
                        authToken,
                        it
                    ) as Call<Any>

                    else -> RetrofitClient.instance.getArtistDetails(
                        authToken,
                        it
                    ) as Call<Any>
                }
            }

            call?.enqueue(object : Callback<Any> {
                override fun onResponse(
                    call: Call<Any>,
                    response: Response<Any>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            if(type == "album")
                            {val album = responseBody as Album
                                albumNameTextView.text = album.name
                                albumPopularityTextView.text = ""
                                val imageUrl = album.images[0].url
                                Glide.with(this@DetailsActivity)
                                    .load(imageUrl)
                                    .into(albumImageView)}
                            else if(type == "artist")
                            {val artist = responseBody as Artist
                                albumNameTextView.text = artist.name
                                albumPopularityTextView.text = ""
                                val imageUrl = artist.images[0].url
                                Glide.with(this@DetailsActivity)
                                    .load(imageUrl)
                                    .into(albumImageView)
                            }
                            else{
                                val track = responseBody as Track
                                albumNameTextView.text = track.name
                                albumPopularityTextView.text = ""
                                val imageUrl = track.images[0].url
                                Glide.with(this@DetailsActivity)
                                    .load(imageUrl)
                                    .into(albumImageView)
                            }
                        }
                    } else {
                        Log.e(
                            "Retrofit",
                            "Failed to fetch artist details: ${response.code()}"
                        )
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.e("Retrofit", "Error: ${t.message}", t)
                }
            })

        } else {

        }


    }
}