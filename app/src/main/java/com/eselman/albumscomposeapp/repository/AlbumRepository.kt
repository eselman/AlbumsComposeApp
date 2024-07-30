package com.eselman.albumscomposeapp.repository

import com.eselman.albumscomposeapp.model.Album
import com.eselman.albumscomposeapp.model.AlbumState

interface AlbumRepository {
    suspend fun getAlbums(): AlbumState
    suspend fun refreshAlbums(): AlbumState
    suspend fun getAlbumById(albumId: String): Album?
}
