package com.example.myapplication.model

data class TrackResponse(
    val tracks: List<Track>
)

data class Track(
    val id: String,
    val name: String,
    val images: List<TrackImage>
)

data class TrackImage(
    val url: String
)
