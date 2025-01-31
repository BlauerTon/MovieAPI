package com.example.moviescreen


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviescreen.MovieAppScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppScreen()
        }
    }

    // Go back to the previous screen
//    fun goBack() {
//        navController.popBackStack()
//    }
}

