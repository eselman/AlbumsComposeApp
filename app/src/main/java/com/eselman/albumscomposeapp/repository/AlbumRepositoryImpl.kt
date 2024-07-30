package com.eselman.albumscomposeapp.repository

import com.eselman.albumscomposeapp.database.dao.AlbumDao
import com.eselman.albumscomposeapp.model.Album
import com.eselman.albumscomposeapp.model.AlbumState
import com.eselman.albumscomposeapp.network.responses.toAlbumRealm
import com.eselman.albumscomposeapp.network.services.AlbumsService
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val albumsService: AlbumsService
) : AlbumRepository {
    override suspend fun getAlbums(): AlbumState {
        val albumsFromDatabase = albumDao.getAllAlbums()

        val albumState = if (albumsFromDatabase.isEmpty()) {
            refreshAlbums()
        } else {
            AlbumState(
                albums = albumsFromDatabase.map { it.toAlbum() },
                isError = false
            )
        }
        return albumState
    }

    override suspend fun refreshAlbums(): AlbumState {
        val albumState = try {
            // Get Albums from Service
            val albumsFromService = albumsService.getAllAlbums()
            if (albumsFromService.feed.albums.isNotEmpty()) {
                val albums = albumsFromService.feed.albums.map {
                    it.toAlbumRealm(albumsFromService.feed.copyright ?: "")
                }

                // Clear all database records
                albumDao.deleteAllAlbums()

                // Insert new records in the database
                albumDao.insertAlbums(albums)
                AlbumState(
                    albums = albumDao.getAllAlbums().map { it.toAlbum() },
                    isError = false
                )
            } else {
                AlbumState(
                    albums = null,
                    isError = true
                )
            }
        } catch (e: Exception) {
            AlbumState(
                albums = null,
                isError = true
            )
        }

        return albumState
    }

    override suspend fun getAlbumById(albumId: String): Album? =
        albumDao.getAlbumById(albumId)?.toAlbum()
}
