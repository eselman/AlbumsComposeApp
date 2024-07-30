package com.eselman.albumscomposeapp.network.responses

import android.os.Parcelable
import com.eselman.albumscomposeapp.database.entities.AlbumRealm
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumResponse(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val genres: List<GenreResponse>?,
    val url: String?
): Parcelable

fun AlbumResponse.toAlbumRealm(copyright: String): AlbumRealm {
    val genre = genres?.let {
        if (it.size > 1) {
            it.firstOrNull { genre -> genre.name != "Music" }
        } else {
            it[0]
        }
    }

    return AlbumRealm(
        id = id,
        name = name,
        artistName = artistName,
        artworkUrl100 = artworkUrl100,
        releaseDate = releaseDate,
        genre = genre?.name ?: "",
        copyright = copyright,
        url = url ?: ""
    )
}
