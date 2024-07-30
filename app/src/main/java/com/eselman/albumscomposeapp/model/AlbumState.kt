package com.eselman.albumscomposeapp.model

data class AlbumState(
    val albums: List<Album>?,
    val isError: Boolean
)
