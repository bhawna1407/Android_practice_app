package com.example.myapplication.retrofit

import com.example.myapplication.model.Album
import com.example.myapplication.model.AlbumResponse
import com.example.myapplication.model.Artist
import com.example.myapplication.model.ArtistResponse
import com.example.myapplication.model.SearchResult
import com.example.myapplication.model.Track
import com.example.myapplication.model.TrackResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApiService {

    @GET("search?type=album,track,playlist,artist")
    fun search(
        @Header("Authorization") authToken: String,
        @Query("q") query: String,
    ): Call<SearchResult>

        @GET("artists/{artistId}")
        fun getArtistDetails(
            @Header("Authorization") authToken: String,
            @Path("artistId") artistId: String
        ): Call<Artist>

    @GET("albums/{albumId}")
    fun getAlbumDetails(
        @Header("Authorization") authToken: String,
        @Path("albumId") albumId: String
    ): Call<Album>

    @GET("tracks/{trackId}")
    fun getTrackDetails(
        @Header("Authorization") authToken: String,
        @Path("trackId") trackId: String
    ): Call<Track>

    @GET("albums")
    fun getAlbums(
        @Header("Authorization") authToken: String,
        @Query("ids") ids: String
    ): Call<AlbumResponse>

    @GET("tracks")
    fun getTracks(
        @Header("Authorization") authToken: String,
        @Query("ids") trackIds: String
    ): Call<TrackResponse>

    @GET("artists")
    fun getArtists(
        @Header("Authorization") authToken: String,
        @Query("ids") artistIds: String
    ): Call<ArtistResponse>

    @GET("artists")
    fun getSeveralArtists(
        @Header("Authorization") authToken: String,
        @Query("ids") artistIds: String
    ): Call<ArtistResponse>

    @GET("albums")
    fun getSeveralAlbums(
        @Header("Authorization") authToken: String,
        @Query("ids") artistIds: String
    ): Call<AlbumResponse>

    @GET("tracks")
    fun getSeveralTracks(
        @Header("Authorization") authToken: String,
        @Query("ids") artistIds: String
    ): Call<TrackResponse>

}
