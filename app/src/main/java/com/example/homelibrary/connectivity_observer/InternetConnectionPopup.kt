package com.example.homelibrary.connectivity_observer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.homelibrary.R
import com.example.homelibrary.presentation.common.buttons.TealButton

@Composable
fun InternetConnectionPopup(
    showPopup: Boolean,
    onDismiss: () -> Unit
) {
    if (showPopup) {
        AlertDialog(
            onDismissRequest = onDismiss,
            icon = {Icons.Rounded.ErrorOutline},
            title = {
                Text(
                    text = stringResource(R.string.no_internet_connection),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.please_check_your_internet_connection),
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                TealButton(
                    onClick = onDismiss,
                    label = stringResource(R.string.ok)
                )
            }
        )
    }
}