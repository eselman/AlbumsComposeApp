package com.eselman.albumscomposeapp.ui.composables

import android.content.res.Configuration
import android.webkit.URLUtil
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.eselman.albumscomposeapp.Destination
import com.eselman.albumscomposeapp.model.Album
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AlbumDetailsView(
    album: Album,
    navController: NavHostController?,
    configuration: Configuration
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    if (openAlertDialog.value) {
        AlertDialogView {
            openAlertDialog.value = false
        }
    }

    val btnClick = {
        if (URLUtil.isValidUrl(album.url)) {
            openAlertDialog.value = false
            val encodedUrl = URLEncoder.encode(album.url, StandardCharsets.UTF_8.toString())
            navController?.navigate(Destination.AlbumsWebView.createRoute(encodedUrl))
        } else {
            openAlertDialog.value = true
        }
    }

    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        VerticalAlbumDetailsView(
            album = album
        ) {
            btnClick()
        }
    } else {
        HorizontalAlbumDetailsView(
            album = album
        ) {
            btnClick()
        }
    }
}
