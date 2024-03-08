package com.example.myapplication.model

data class SearchResult(
    val tracks: SearchItemResponse,
    val artists: SearchItemResponse,
    val albums: SearchItemResponse,
    val playlists: SearchItemResponse
)

data class SearchItemResponse(
    val items: List<SearchItem>
)

data class SearchItem(
    val name: String,
    val type: String,
    val uri: String,
    val id: String
)

data class Artist(
    val id: String,
    val name: String,
    val genres: List<String>,
    val popularity: Int,
    val images: List<Image>
)

data class Image(
    val url: String,
    val height: Int,
    val width: Int
)




