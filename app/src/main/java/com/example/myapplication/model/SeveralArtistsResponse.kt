package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class ArtistsResponse(
    val artists: List<Artist>
)

data class SeveralArtistsResponse(
    val external_urls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<SeveralArtistsImage>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)

data class ExternalUrls(
    val spotify: String
)

data class Followers(
    val href: String?,
    val total: Int
)

data class SeveralArtistsImage(
    val url: String,
    val height: Int,
    val width: Int
)

