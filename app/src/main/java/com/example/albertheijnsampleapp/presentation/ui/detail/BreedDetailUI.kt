package com.example.albertheijnsampleapp.presentation.ui.detail

import androidx.compose.foundation.layout.Box
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
import com.example.albertheijnsampleapp.R
import com.example.albertheijnsampleapp.navigation.NavPath
import com.example.albertheijnsampleapp.presentation.uiaction.UiAction
import com.example.albertheijnsampleapp.presentation.ui.ErrorSnackbar
import com.example.albertheijnsampleapp.presentation.ui.LoadingIndicator
import com.example.albertheijnsampleapp.presentation.ui.TopAppBarUI
import com.example.albertheijnsampleapp.utils.rememberFlowWithLifecycle

/**
 * Composable function that represents the UI for the breed detail screen.
 *
 * @param navHostController The NavHostController used for navigation.
 * @param viewModel The [BreedDetailViewModel] used to manage the state and data for the screen.
 */
@Composable
fun BreedDetailUI(navHostController: NavHostController, viewModel: BreedDetailViewModel) {

    // Collect the breedDetailState using rememberFlowWithLifecycle to automatically collect and
    // recompose the UI whenever the state changes.
    val userDetailState by rememberFlowWithLifecycle(viewModel.breedDetailState)
        .collectAsState(initial = null)

    // Scaffold is a pre-defined Composable that provides a basic Material Design layout structure
    // for the screen.
    Scaffold(
        topBar = {
            // TopAppBarUI is a custom Composable that represents the top app bar with a back button.
            TopAppBarUI(
                title = stringResource(id = R.string.breed_detail_title),
                onBackClick = {
                    // Navigate back to the BreedList screen when the back button is clicked.
                    navHostController.popBackStack(
                        NavPath.BreedList.route,
                        inclusive = false
                    )
                },
                showBackIcon = true,
                backButtonDescription = "Detail Page Back Button"
            )
        }
    ) {
        // Box is a Composable that allows stacking other Composables on top of each other.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues = it)
        ) {

            // Based on the current state, show the appropriate UI components.
            when (val state = userDetailState) {
                is BreedDetailState.Error -> {
                    // Show an error Snackbar with the corresponding error message.
                    val errorMessageId = state.errorMessage
                    ErrorSnackbar(
                        errorMessage = stringResource(id = errorMessageId),
                        onRetry = { viewModel.onUiAction(UiAction.RetryAction) },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(8.dp)
                    )
                }

                BreedDetailState.Loading -> {
                    // Show a loading indicator while the data is being fetched.
                    LoadingIndicator()
                }

                is BreedDetailState.Success -> {
                    // Show the breed detail information when the data is successfully fetched.
                    val breedResult = state.userDetail
                    DetailUI(breedDetail = breedResult)
                }

                null -> {
                    // Show an error Snackbar with a generic error message.
                    ErrorSnackbar(
                        errorMessage = "Unknown Error",
                        onRetry = { viewModel.onUiAction(UiAction.RetryAction) },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
