package com.example.albertheijnsampleapp.presentation.ui.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function that displays a row with a title and a value.
 *
 * @param title The title to be displayed.
 * @param value The value to be displayed.
 */
@Composable
fun tableRow(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.weight(2.5f)
        )
    }
}
