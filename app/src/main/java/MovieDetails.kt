package com.example.moviescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    // Movie Poster
                    Image(
                        painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(bottom = 16.dp)
                    )

                    // Movie Title
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Movie Details (Year, Duration, Genre)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "2021",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "•",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = movie.duration,
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "•",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0)),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = movie.genre,
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFA0A0A0))
                        )
                    }

                    // Play Button
                    Button(
                        onClick = {
                            // Handle play button click
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 32.dp, vertical = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF12CDD9)
                        )
                    ) {
                        Text(
                            text = "Play",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    // Storyline
                    Text(
                        text = "Story Line",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = movie.description ?: "No description available.",
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