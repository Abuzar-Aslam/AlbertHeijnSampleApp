package com.example.albertheijnsampleapp.presentation.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.albertheijnsampleapp.R
import com.example.albertheijnsampleapp.navigation.NavPath
import com.example.albertheijnsampleapp.presentation.uiaction.UiAction
import com.example.albertheijnsampleapp.presentation.ui.ErrorSnackbar
import com.example.albertheijnsampleapp.presentation.ui.LoadingIndicator
import com.example.albertheijnsampleapp.presentation.ui.TopAppBarUI

/**
 * Composable function for the main screen of the app.
 *
 * @param viewModel The instance of MainViewModel to interact with the screen's state and events.
 */
@Composable
fun BreedLisUI(navHostController: NavHostController, viewModel: BreedListViewModel) {
    val list = viewModel.pagingData.collectAsLazyPagingItems()

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBarUI(title = stringResource(id = R.string.breed_listing_title)) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues = it)
        ) {
            when (list.loadState.refresh) {
                LoadState.Loading -> {
                    LoadingIndicator()
                }

                is LoadState.Error -> {
                    val loadState = list.loadState.refresh as LoadState.Error

                    if (uiState.errorMessageId != 0) {
                        ErrorSnackbar(
                            errorMessage = stringResource(id = uiState.errorMessageId),
                            onRetry = { viewModel.onUiAction(UiAction.RetryAction) },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(8.dp)
                        )
                    } else {
                        viewModel.onUiAction(UiAction.HandleErrorAction(loadState.error))
                    }
                }

                else -> {
                    Column(
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        displayBreedList(list = list, modifier = Modifier) { id ->
                            navHostController.navigate(
                                "${NavPath.BreedDetail.route}?id=${id}"
                            )
                        }
                    }
                }
            }
        }
    }
}
