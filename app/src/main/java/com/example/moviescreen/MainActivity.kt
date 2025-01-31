package com.example.moviescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviescreen.ui.theme.MovieScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieScreenTheme {
                // Set up the NavController
                val navController = rememberNavController()

                // Define the navigation graph
                NavHost(
                    navController = navController,
                    startDestination = "movieList"
                ) {
                    // MovieListScreen route
                    composable("movieList") {
                        MovieListScreen(navController = navController)
                    }

                    // MovieDetailsScreen route
                    composable("movieDetail/{movieId}") { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movieId")
                        MovieDetailsScreen(navController = navController, movieId = movieId)
                    }
                }
            }
        }
    }
}