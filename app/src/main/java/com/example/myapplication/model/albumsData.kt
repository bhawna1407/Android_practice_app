package com.example.myapplication.model

data class AlbumResponse(
    val albums: List<Album>
)

data class Album(
    val id: String,
    val name: String,
    val images: List<AlbumImage>
)

data class AlbumImage(
    val url: String
)