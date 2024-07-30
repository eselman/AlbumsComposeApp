package com.eselman.albumscomposeapp.ui.screens

import android.net.http.SslError
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.eselman.albumscomposeapp.R
import com.eselman.albumscomposeapp.ui.composables.AppBar
import com.eselman.albumscomposeapp.ui.composables.ErrorMessageView
import com.eselman.albumscomposeapp.viewmodel.AlbumPageViewModel

@Composable
fun AlbumWebViewScreen(
    albumPageViewModel: AlbumPageViewModel,
    navController: NavHostController?
) {
    val albumUrl = albumPageViewModel.albumUrl.collectAsState()
    val showWebViewError = remember { mutableStateOf(false) }

    val context = LocalContext.current

    var webView = remember {
        WebView(context).apply {
            webViewClient = object: WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    showWebViewError.value = true
                }

                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    super.onReceivedHttpError(view, request, errorResponse)
                    showWebViewError.value = true
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    super.onReceivedSslError(view, handler, error)
                    showWebViewError.value = true
                }
            }

            // This line is to avoid the screen flickering when navigating.
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)

            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)
            albumUrl.value?.let {
                loadUrl(it)
            }
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.album_webview_title),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            ) {
                handleBack(
                    navController,
                    webView
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                if (showWebViewError.value) {
                    ErrorMessageView(
                        stringResource(id = R.string.album_webview_error_message)
                    )
                } else {
                    AndroidView(
                        modifier = Modifier
                            .fillMaxSize(),
                        factory = { webView },
                        update = {
                            webView = it
                        })
                }

                BackHandler {
                    handleBack(
                        navController,
                        webView
                    )
                }
            }
        }
    }
}

private fun handleBack(
    navController: NavHostController?,
    webView: WebView
) {
    if (webView.canGoBack()) {
        webView.goBack()
    } else {
        navController?.popBackStack()
    }
}
