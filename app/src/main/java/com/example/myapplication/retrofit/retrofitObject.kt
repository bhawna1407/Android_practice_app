package com.example.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var BASE_URL = "https://api.spotify.com/v1/"

    fun setBaseUrl(baseUrl: String) {
        BASE_URL = baseUrl
    }

    val instance: SpotifyApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SpotifyApiService::class.java)
    }
}
