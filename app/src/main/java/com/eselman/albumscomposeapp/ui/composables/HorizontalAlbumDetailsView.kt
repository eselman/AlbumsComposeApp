package com.eselman.albumscomposeapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.eselman.albumscomposeapp.R
import com.eselman.albumscomposeapp.model.Album
import com.eselman.albumscomposeapp.ui.theme.CustomRed
import com.eselman.albumscomposeapp.utils.extractYearFromDate

@Composable
fun HorizontalAlbumDetailsView(
    album: Album,
    btnOnClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = album.artworkUrl100,
            placeholder = painterResource(id = R.drawable.baseline_music_note_24),
            error = painterResource(id = R.drawable.baseline_music_note_24),
            contentDescription = stringResource(id = R.string.album_thumbnail_content_description),
            modifier = Modifier
                .weight(1f)
                .width(200.dp)
                .height(200.dp)
                .padding(horizontal = 32.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = album.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = album.artistName,
                color = CustomRed,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(
                    id = R.string.genre_year,
                    album.genre,
                    album.releaseDate.extractYearFromDate()
                ),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = btnOnClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = CustomRed
                )
            ) {
                Text(
                    text = stringResource(id = R.string.go_to_itunes_btn),
                    color = Color.White
                )
            }

            Text(
                text = album.copyright,
                fontSize = 14.sp,
                fontWeight = FontWeight.Thin

            )
        }
    }
}