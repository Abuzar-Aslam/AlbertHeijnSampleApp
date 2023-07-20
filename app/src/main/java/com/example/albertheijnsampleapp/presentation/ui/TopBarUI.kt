package com.example.albertheijnsampleapp.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * Composable function that displays a customized TopAppBar.
 *
 * @param title The title to be displayed in the TopAppBar.
 * @param onBackClick The callback function to be invoked when the back icon is clicked.
 * @param showBackIcon Determines whether the back icon should be shown or not. Default value is false.
 * @param backButtonDescription The content description for the back icon, used for accessibility.
 */
@Composable
fun TopAppBarUI(
    title: String,
    onBackClick: () -> Unit = {},
    showBackIcon: Boolean = false,
    backButtonDescription: String = ""
) {
    TopAppBar(
        title = {
            Text(title, textAlign = TextAlign.Center)
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        modifier = Modifier,
                        contentDescription = backButtonDescription
                    )
                }
            }
        }
    )
}
