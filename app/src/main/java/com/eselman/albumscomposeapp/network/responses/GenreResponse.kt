package com.eselman.albumscomposeapp.network.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponse(
    val name: String
): Parcelable
