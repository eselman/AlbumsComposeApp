package com.eselman.albumscomposeapp.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eselman.albumscomposeapp.Destination
import com.eselman.albumscomposeapp.model.Album
import com.eselman.albumscomposeapp.viewmodel.AlbumsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumsListView(
    albums: List<Album>,
    navController: NavHostController?,
    albumsListViewModel: AlbumsListViewModel,
    isRefreshing: Boolean,
    configuration: Configuration
) {
    val onRefresh: () -> Unit = {
        albumsListViewModel.refreshAlbums()
    }

    PullToRefreshBox(
        state = rememberPullToRefreshState(),
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
    ) {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(albums) { item ->
                    VerticalAlbumCard(item,
                        Modifier
                            .fillMaxHeight()
                            .defaultMinSize(minHeight = 300.dp)
                            .clickable {
                                navController?.navigate(Destination.AlbumDetails.createRoute(item.id))
                            })
                }
            }
        } else {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.heightIn(max = 300.dp)
                ) {
                    items(albums) { item ->
                        HorizontalAlbumCard(
                            item,
                            Modifier
                                .width(300.dp)
                                .clickable {
                                    navController?.navigate(
                                        Destination.AlbumDetails.createRoute(
                                            item.id
                                        )
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}
