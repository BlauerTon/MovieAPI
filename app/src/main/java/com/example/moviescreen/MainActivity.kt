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
                    startDestination = "onboarding1" // Start with onboarding1
                ) {
                    // Onboarding screens
                    composable("onboarding1") {
                        OnboardingOne(navigationManager)
                    }
                    composable("onboarding2") {
                        OnboardingTwo(navigationManager)
                    }
                    composable("onboarding3") {
                        OnboardingThree(navigationManager)
                    }

                    // Home screen (MovieListScreen)
                    composable("HomeScreen") {
                        MovieListScreen(navController = navController)
                    }

                    // Movie detail screen
                    composable("movieDetail/{movieId}") { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                        if (movieId != null) {
                            MovieDetailsScreen(navController = navController, movieId = movieId.toString())
                        }
                    }
                }
            }
        }
    }
}

class NavigationManager(private val navController: NavHostController) {

    // Navigate to Onboarding1
    fun navigateToOnboarding1() {
        navController.navigate("onboarding1") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    // Navigate to Onboarding2
    fun navigateToOnboarding2() {
        navController.navigate("onboarding2") {
            launchSingleTop = true
        }
    }

    // Navigate to Onboarding3
    fun navigateToOnboarding3() {
        navController.navigate("onboarding3") {
            launchSingleTop = true
        }
    }

    // Navigate to HomeScreen
    fun navigateToHomeScreen() {
        navController.navigate("HomeScreen") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    // Navigate to Movie Detail screen
    fun navigateToMovieDetail(movieId: Int) {
        navController.navigate("movieDetail/$movieId") {
            launchSingleTop = true
        }
    }

    // Go back to the previous screen
    fun goBack() {
        navController.popBackStack()
    }
}