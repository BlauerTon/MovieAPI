package com.example.moviescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviescreen.com.example.moviescreen.MovieDetailsScreen
import com.example.moviescreen.ui.theme.MovieScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieScreenTheme {
                val navController = rememberNavController()
                val navigationManager = remember { NavigationManager(navController) }
                val movieViewModel: MovieViewModel = viewModel()

                // Navigation graph
                NavHost(
                    navController = navController,
                    startDestination = "onboarding"
                ) {
                    // Home screen
                    composable("HomeScreen") {
                        HomeScreen(navController = navController)
                    }

                    composable("onboarding") {
                        OnboardingScreen(navController = navController)
                    }

                    composable("search") {
                        MovieListScreen(navController = navController)
                    }

                    // Downloads screen
                    composable("downloads") {
                        DownloadsScreen(navController = navController)
                    }

                    // Movie detail screen
                    composable("movieDetail/{movieId}") { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                        if (movieId != null) {
                            MovieDetailsScreen(navController = navController, movieId = movieId.toString())
                        }
                    }

                    // Profile screen
                    composable("profile") {
                        ProfileScreen(navController = navController)
                    }
                }
            }
        }
    }
}

class NavigationManager(private val navController: NavHostController) {
    fun navigateToHomeScreen() {
        navController.navigate("HomeScreen") {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToOnboarding() {
        navController.navigate("onboarding") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    fun navigateToSearch() {
        navController.navigate("search") {
            launchSingleTop = true
        }
    }

    fun navigateToDownloads() {
        navController.navigate("downloads") {
            launchSingleTop = true
        }
    }

    fun navigateToMovieDetail(movieId: Int) {
        navController.navigate("movieDetail/$movieId") {
            launchSingleTop = true
        }
    }

    fun navigateToProfile() {
        navController.navigate("profile") {
            launchSingleTop = true
        }
    }

    fun goBack() {
        navController.popBackStack()
    }
}