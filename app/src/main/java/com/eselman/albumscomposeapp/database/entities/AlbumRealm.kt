package com.eselman.albumscomposeapp.database.entities

import com.eselman.albumscomposeapp.model.Album
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class AlbumRealm() : RealmObject {
    @PrimaryKey
    private var id: String = ""
    private var name: String = ""
    private var artistName: String = ""
    private var artworkUrl100: String = ""
    private var releaseDate: String = ""
    private var genre: String = ""
    private var copyright: String = ""
    private var url: String = ""

    constructor(
        id: String,
        name: String,
        artistName: String,
        artworkUrl100: String,
        releaseDate: String,
        genre: String,
        copyright: String,
        url: String
    ): this(){
        this.id = id
        this.name = name
        this.artistName = artistName
        this.artworkUrl100 = artworkUrl100
        this.releaseDate = releaseDate
        this.genre = genre
        this.copyright = copyright
        this.url = url
    }

    fun toAlbum() = Album(
        id = id,
        name = name,
        artistName = artistName,
        artworkUrl100 = artworkUrl100,
        releaseDate = releaseDate,
        genre = genre,
        copyright = copyright,
        url = url
    )
}
