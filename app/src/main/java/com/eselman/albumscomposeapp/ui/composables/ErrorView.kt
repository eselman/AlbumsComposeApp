package com.eselman.albumscomposeapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eselman.albumscomposeapp.R
import com.eselman.albumscomposeapp.ui.theme.CustomRed
import com.eselman.albumscomposeapp.viewmodel.AlbumsListViewModel

@Composable
fun ErrorView(
    albumsListViewModel: AlbumsListViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.empty_albums_view),
            color = CustomRed,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                albumsListViewModel.refreshAlbums()
            }, colors = ButtonDefaults.outlinedButtonColors(
                containerColor = CustomRed
            )
        ) {
            Text(
                text = stringResource(id = R.string.try_again_btn), color = Color.White
            )
        }
    }
}
