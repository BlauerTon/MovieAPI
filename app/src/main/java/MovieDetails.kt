package com.example.moviescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(navController: NavController, movieId: String?) {
    val movieViewModel: MovieViewModel = viewModel()
    val movies by movieViewModel.movies.collectAsState()

    // Find the selected movie by ID
    val movie = movies.find { it.id == movieId?.toInt() }

    Scaffold(
        modifier = Modifier.background(Color(0xFF1F1D2B)),
        containerColor = Color(0xFF1F1D2B),
        topBar = {
            TopAppBar(
                title = { Text(text = movie?.title ?: "Movie Detail", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1F1D2B),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFF1F1D2B))
        ) {
            if (movie != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 16.dp)
                    )

                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Year",
                            tint = Color(0xFFA0A0A0),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Year: ${movie.releaseDate.take(4)}",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Duration",
                            tint = Color(0xFFA0A0A0),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Duration: ${movie.duration}",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Theaters,
                            contentDescription = "Genre",
                            tint = Color(0xFFA0A0A0),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Genre: ${movie.genre}",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Text(
                        text = "Description: ${movie.description ?: "No description available."}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            } else {
                Text(
                    text = "Movie not found",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}