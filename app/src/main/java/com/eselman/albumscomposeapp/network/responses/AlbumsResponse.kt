package com.eselman.albumscomposeapp.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumsResponse(
    val feed: Feed
): Parcelable

@Parcelize
data class Feed(
    val copyright: String?,
    @SerializedName("results")
    val albums: List<AlbumResponse>
): Parcelable
