package com.eselman.albumscomposeapp.model

data class Album(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val genre: String,
    val copyright: String,
    val url: String
)
