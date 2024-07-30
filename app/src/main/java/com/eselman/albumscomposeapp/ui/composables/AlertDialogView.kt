package com.eselman.albumscomposeapp.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.eselman.albumscomposeapp.R

@Composable
fun AlertDialogView(
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.go_to_itunes_invalid_url_title))
        },
        text = {
            Text(text = stringResource(id = R.string.go_to_itunes_invalid_url_description))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.go_to_itunes_invalid_url_dismiss_btn))
            }
        }
    )
}
