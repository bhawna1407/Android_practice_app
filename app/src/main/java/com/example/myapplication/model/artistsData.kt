package com.example.myapplication.model

data class ArtistResponse(
    val artists: List<SeveralArtist>
)

data class SeveralArtist(
    val id: String,
    val name: String,
    val images: List<ArtistImage>
)

data class ArtistImage(
    val url: String
)