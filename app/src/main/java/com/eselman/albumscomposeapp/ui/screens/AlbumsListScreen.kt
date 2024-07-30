package com.eselman.albumscomposeapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.eselman.albumscomposeapp.R
import com.eselman.albumscomposeapp.ui.composables.AlbumsListView
import com.eselman.albumscomposeapp.ui.composables.AppBar
import com.eselman.albumscomposeapp.ui.composables.ErrorView
import com.eselman.albumscomposeapp.ui.composables.LoadingView
import com.eselman.albumscomposeapp.viewmodel.AlbumsListViewModel

@Composable
fun AlbumsListScreen(
    albumsListViewModel: AlbumsListViewModel,
    navController: NavHostController?,
    configuration: Configuration
) {
    val albums = albumsListViewModel.albums.collectAsState()
    val isError = albumsListViewModel.isError.collectAsState()
    val isLoading = albumsListViewModel.isLoading.collectAsState()
    val isRefreshing = albumsListViewModel.isRefreshing.collectAsState()

    Scaffold(topBar = {
        AppBar(
            title = stringResource(id = R.string.albums_list_title)
        ) {}
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                isLoading.value -> LoadingView()
                isError.value || albums.value.isEmpty() -> ErrorView(albumsListViewModel)
                else -> AlbumsListView(
                    albums.value,
                    navController,
                    albumsListViewModel,
                    isRefreshing.value,
                    configuration
                )
            }
        }
    }
}
