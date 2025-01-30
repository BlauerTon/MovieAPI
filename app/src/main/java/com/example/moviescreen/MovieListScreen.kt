package com.example.moviescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppScreen() {
    val movieViewModel: MovieViewModel = viewModel()
    val movies by movieViewModel.movies.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    // Filter movies dynamically as the user types
    val filteredMovies = remember(searchQuery, movies) {
        movies.filter { movie ->
            movie.title?.contains(searchQuery, ignoreCase = true) == true
        }
    }

    Scaffold(
        modifier = Modifier.background(Color(0xFF1F1D2B)),
        containerColor = Color(0xFF1F1D2B),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = {
                                Text(
                                    "Type title, categories, years, etc",
                                    color = Color(0xFFA0A0A0)
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFF1F1D2B),
                                unfocusedContainerColor = Color(0xFF1F1D2B),
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(24.dp),
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color(0xFFA0A0A0),
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    // Keyboard hides after search
                                }
                            ),
                            singleLine = true
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1F1D2B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFF1F1D2B))
        ) {
            MovieList(filteredMovies)
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    if (movies.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F1D2B))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://www.figma.com/design/hePDosTHWo48l29bAmjWrA/Cinemax---Movie-Apps-UI-Kit-(Community)?node-id=127-1673&t=TcPhqx3vxa3NigtY-4"),
                    contentDescription = "No Movies Found",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "We Are Sorry, We Can Not Find The Movie :(",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Find your movie by Type title, categories, years, etc",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFA0A0A0))
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F1D2B)),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF292639)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            // Movie Poster
            Image(
                painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 8.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title ?: "Unknown Title",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier.padding(bottom = 4.dp)
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
                        text = "Year: ${movie.releaseDate?.take(4) ?: "Unknown"}",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFA0A0A0)),
                        modifier = Modifier.padding(bottom = 4.dp)
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
                        style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFA0A0A0)),
                        modifier = Modifier.padding(bottom = 4.dp)
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
                        style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFA0A0A0))
                    )
                }
            }
        }
    }
}