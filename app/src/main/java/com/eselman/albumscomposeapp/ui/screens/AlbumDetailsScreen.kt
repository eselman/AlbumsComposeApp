package com.eselman.albumscomposeapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.eselman.albumscomposeapp.R
import com.eselman.albumscomposeapp.ui.composables.AlbumDetailsView
import com.eselman.albumscomposeapp.ui.composables.AppBar
import com.eselman.albumscomposeapp.ui.composables.ErrorMessageView
import com.eselman.albumscomposeapp.viewmodel.AlbumDetailsViewModel

@Composable
fun AlbumDetailsScreen(
    albumDetailsViewModel: AlbumDetailsViewModel,
    navController: NavHostController?,
    configuration: Configuration
) {
    val album = albumDetailsViewModel.album.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                title = album.value?.name ?: stringResource(id = R.string.empty_album_title),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack
            ) {
                navController?.popBackStack()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            album.value?.let {
                AlbumDetailsView(
                    it,
                    navController,
                    configuration
                )
            } ?: ErrorMessageView(
                stringResource(id = R.string.empty_album_details_view)
            )
        }
    }
}
