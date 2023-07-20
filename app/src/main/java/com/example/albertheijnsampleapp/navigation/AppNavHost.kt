package com.example.albertheijnsampleapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.albertheijnsampleapp.presentation.ui.detail.BreedDetailUI
import com.example.albertheijnsampleapp.presentation.ui.detail.BreedDetailViewModel
import com.example.albertheijnsampleapp.presentation.ui.list.BreedListViewModel
import com.example.albertheijnsampleapp.presentation.ui.list.BreedLisUI

/**
 * Enumeration representing the navigation paths in the app.
 *
 * @param route The string representation of the route for each navigation path.
 */
enum class NavPath(
    val route: String,
) {
    BreedList(route = "breed_listing"),
    BreedDetail(route = "breed_detail")
}

/**
 * The main navigation host composable for the app.
 *
 * @param navHostController The navigation controller used to navigate between destinations.
 */
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavPath.BreedList.route) {
        // Composable for the breed list screen
        composable(NavPath.BreedList.route) {
            val breedListViewModel = hiltViewModel<BreedListViewModel>()
            BreedLisUI(navHostController = navHostController, viewModel = breedListViewModel)
        }

        // Composable for the breed detail screen with the "id" argument
        composable(
            "${NavPath.BreedDetail.route}?id={id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                })
        ) {
            val breedDetailViewModel = hiltViewModel<BreedDetailViewModel>()
            BreedDetailUI(navHostController = navHostController, viewModel = breedDetailViewModel)
        }
    }
}
