package com.example.myapplication.ui.dashboard
import SearchAdapter
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.SearchResult
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {

    val searchResultLiveData = MutableLiveData<SearchResult>()
    val errorLiveData = MutableLiveData<String>()
    private val _typesLiveData = MutableLiveData<List<String>>()
    val typesLiveData: LiveData<List<String>> get() = _typesLiveData
    private val _triplesLiveData = MutableLiveData<List<Triple<String, String, String>>>()
    val triplesLiveData: LiveData<List<Triple<String, String, String>>> get() = _triplesLiveData
    private val _namesAndIdsLiveData = MutableLiveData<List<Pair<String, String>>>()
    val namesAndIdsLiveData: LiveData<List<Pair<String, String>>> = _namesAndIdsLiveData



    fun searchSpotify(authToken: String, query: String) {
        RetrofitClient.instance.search(authToken, query)
            .enqueue(object : Callback<SearchResult> {
                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val triples = mutableListOf<Triple<String, String, String>>()

                        result?.albums?.items?.forEach { album ->
                            triples.add(Triple(album.name, album.id, album.type))
                        }
                        result?.artists?.items?.forEach { artist ->
                            triples.add(Triple(artist.name, artist.id, artist.type))
                        }
                        result?.tracks?.items?.forEach { track ->
                            triples.add(Triple(track.name, track.id, track.type))
                        }
                        _triplesLiveData.postValue(triples)
                    } else {
                        errorLiveData.postValue("Failed to fetch data")
                    }
                }
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    errorLiveData.postValue("Failed to connect to the server")
                }
            })
    }
}
