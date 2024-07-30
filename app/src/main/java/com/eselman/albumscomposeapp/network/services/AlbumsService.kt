package com.eselman.albumscomposeapp.network.services

import com.eselman.albumscomposeapp.network.responses.AlbumsResponse
import retrofit2.http.GET

interface AlbumsService {
    @GET("api/v2/us/music/most-played/100/albums.json")
    suspend fun getAllAlbums(): AlbumsResponse
}
