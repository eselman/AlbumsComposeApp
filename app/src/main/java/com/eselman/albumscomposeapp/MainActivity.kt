package com.eselman.albumscomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eselman.albumscomposeapp.ui.screens.AlbumDetailsScreen
import com.eselman.albumscomposeapp.ui.screens.AlbumWebViewScreen
import com.eselman.albumscomposeapp.ui.screens.AlbumsListScreen
import com.eselman.albumscomposeapp.ui.theme.AlbumsComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class Destination(val route: String) {
    data object AlbumsList : Destination("albums_list")
    data object AlbumDetails : Destination("album/{albumId}") {
        fun createRoute(albumId: String) = "album/$albumId"
    }

    data object AlbumsWebView : Destination("album/webview/{albumUrl}") {
        fun createRoute(albumUrl: String) = "album/webview/$albumUrl"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlbumsComposeAppTheme {
                AlbumsApp()
            }
        }
    }
}

@Composable
private fun AlbumsApp() {
    val navController = rememberNavController()
    val configuration = LocalConfiguration.current

    NavHost(
        navController,
        startDestination = Destination.AlbumsList.route
    ) {
        composable(route = Destination.AlbumsList.route) {
            AlbumsListScreen(
                albumsListViewModel = hiltViewModel(),
                navController = navController,
                configuration = configuration
            )
        }

        composable(
            route = Destination.AlbumDetails.route,
            arguments = listOf(navArgument("albumId") {
                type = NavType.StringType
            })
        ) {
            AlbumDetailsScreen(
                albumDetailsViewModel = hiltViewModel(),
                navController = navController,
                configuration = configuration
            )
        }

        composable(
            route = Destination.AlbumsWebView.route,
            arguments = listOf(navArgument("albumUrl") {
                type = NavType.StringType
            })
        ) {
            AlbumWebViewScreen(
                albumPageViewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}
