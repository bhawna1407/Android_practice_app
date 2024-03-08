package com.example.myapplication.ui.home
import SearchAdapter
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Album
import com.example.myapplication.model.AlbumResponse
import com.example.myapplication.model.ArtistResponse
import com.example.myapplication.model.ArtistsResponse
import com.example.myapplication.model.SearchResult
import com.example.myapplication.model.SeveralArtist
import com.example.myapplication.model.Track
import com.example.myapplication.model.TrackResponse
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val albumsLiveData = MutableLiveData<List<Album>>()
    val trackLiveData = MutableLiveData<List<Track>>()
    val artistsLiveData = MutableLiveData<List<SeveralArtist>>()

    fun getSeveralArtists(authToken: String, artistIds: List<String>) {
        val idsString = artistIds.joinToString(separator = ",")
        val call = RetrofitClient.instance.getSeveralArtists(authToken, idsString)
        call.enqueue(object : Callback<ArtistResponse> {
            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                if (response.isSuccessful) {
                    val artistsResponse = response.body()
                    val artists = artistsResponse?.artists ?: emptyList()
                    artistsLiveData.postValue(artists)
                    artists?.forEach { artist ->

                    }
                } else {

                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {

            }
        })
    }

    fun getSeveralAlbums(authToken: String, albumIds: List<String>) {
        val idsString = albumIds.joinToString(separator = ",")
        val call = RetrofitClient.instance.getSeveralAlbums(authToken, idsString)
        call.enqueue(object : Callback<AlbumResponse> {
            override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {
                if (response.isSuccessful) {
                    val albumsResponse = response.body()
                    val albums = albumsResponse?.albums ?: emptyList()
                    albumsLiveData.postValue(albums)
                } else {

                }
            }
            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getSeveralTracks(authToken: String, trackIds: List<String>) {
        val idsString = trackIds.joinToString(separator = ",")
        val call = RetrofitClient.instance.getSeveralTracks(authToken, idsString)
        call.enqueue(object : Callback<TrackResponse> {
            override fun onResponse(call: Call<TrackResponse>, response: Response<TrackResponse>) {
                if (response.isSuccessful) {
                    val tracksResponse = response.body()
                    val tracks = tracksResponse?.tracks ?: emptyList()
                    trackLiveData.postValue(tracks)
                } else {

                }
            }
            override fun onFailure(call: Call<TrackResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}

