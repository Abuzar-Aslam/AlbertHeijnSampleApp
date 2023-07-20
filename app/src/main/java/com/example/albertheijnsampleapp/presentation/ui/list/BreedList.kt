package com.example.albertheijnsampleapp.presentation.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.albertheijnsampleapp.presentation.model.list.BreedItem
import com.example.albertheijnsampleapp.ui.theme.lightGrey

/**
 * Composable function for displaying a list of breed items.
 *
 * @param list The lazy paging items representing the breeds to display.
 * @param modifier The modifier to be applied to the root composable.
 */
@Composable
fun displayBreedList(
    list: LazyPagingItems<BreedItem>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(color = lightGrey)
            .padding(top = 8.dp)
    ) {
        items(list.itemCount) { index ->
            list[index]?.let {
                BetItem(
                    item = it,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    onItemClick
                )
            }
        }
    }
}