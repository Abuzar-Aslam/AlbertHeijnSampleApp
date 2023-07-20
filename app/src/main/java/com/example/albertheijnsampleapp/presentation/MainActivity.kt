package com.example.albertheijnsampleapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.example.albertheijnsampleapp.navigation.AppNavHost
import com.example.albertheijnsampleapp.ui.theme.AlbertHeijnSampleAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the app.
 *
 * This activity sets up the content view using Jetpack Compose and displays the UI defined in the MainUI composable function.
 * The MainViewModel is injected using Hilt's viewModel delegate.
 */

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view using Jetpack Compose
        setContent {

            val navController = rememberNavController()

            AlbertHeijnSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppNavHost(navController)
                }
            }
        }
    }
}