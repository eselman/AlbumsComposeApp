package com.eselman.albumscomposeapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.eselman.albumscomposeapp.R
import com.eselman.albumscomposeapp.ui.theme.CustomRed

@Composable
fun ErrorMessageView(
    errorMessage: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = errorMessage,
            color = CustomRed,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
