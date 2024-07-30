package com.eselman.albumscomposeapp.database.dao

import com.eselman.albumscomposeapp.database.entities.AlbumRealm

interface AlbumDao {
    suspend fun insertAlbums(albums: List<AlbumRealm>)
    suspend fun getAllAlbums(): List<AlbumRealm>
    suspend fun getAlbumById(id: String): AlbumRealm?
    suspend fun deleteAllAlbums()
}
