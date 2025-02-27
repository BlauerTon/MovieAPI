package com.example.moviescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.moviescreen.Movie // Import the Movie data class from Model.kt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val backgroundColor = Color(0xFF1F1D2B) // Dark background
    val textColor = Color.White
    val iconColor = Color(0xFF12CDD9) // Cyan accent
    val grayTextColor = Color(0xFFA0A0A0)

    val movieViewModel: MovieViewModel = viewModel()
    val movies by movieViewModel.movies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()

    Scaffold(
        modifier = Modifier.background(backgroundColor),
        containerColor = backgroundColor,
        bottomBar = {
            CustomBottomNavigation(
                selectedTab = 0, // Set to 0 for "Home"
                onTabSelected = { index ->
                    when (index) {
                        0 -> navController.navigate("HomeScreen") {
                            popUpTo("HomeScreen") { saveState = true }
                            launchSingleTop = true
                        }
                        1 -> navController.navigate("search")
                        2 -> navController.navigate("downloads")
                        3 -> navController.navigate("profile")
                    }
                },
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(backgroundColor)
                .padding(horizontal = 16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter("https://via.placeholder.com/40"),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Hello, Smith",
                            color = textColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Let’s stream your favorite movie",
                            color = grayTextColor,
                            fontSize = 14.sp
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Search Bar
            var searchQuery by remember { mutableStateOf("") }
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        "Search a title...",
                        color = grayTextColor,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF252836),
                    unfocusedContainerColor = Color(0xFF252836),
                    focusedBorderColor = iconColor,
                    unfocusedBorderColor = grayTextColor
                ),
                shape = RoundedCornerShape(24.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = textColor
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = grayTextColor,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = "More Options",
                        tint = grayTextColor,
                        modifier = Modifier.size(20.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // Filter movies based on search query
                    }
                ),
                singleLine = true
            )

            // Featured Movie Section
            if (movies.isNotEmpty() && !isLoading) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF252836))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(movies[0].getPosterUrl()),
                            contentDescription = movies[0].title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movies[0].title,
                            color = textColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "On ${movies[0].releaseDate}",
                            color = grayTextColor,
                            fontSize = 14.sp
                        )
                    }
                }
            } else if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp).padding(vertical = 16.dp)) {
                    CircularProgressIndicator(color = iconColor, modifier = Modifier.align(Alignment.Center))
                }
            }

            // Categories Section
            Text(
                text = "Categories",
                color = textColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyRow(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOf("All", "Comedy", "Animation", "Dokument")) { category ->
                    FilterChip(
                        selected = false, // Explicitly set selected parameter
                        enabled = true, // Explicitly set enabled parameter
                        onClick = { /* Handle category selection */ },
                        label = { Text(category, color = textColor) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color(0xFF252836),
                            selectedContainerColor = iconColor,
                            labelColor = textColor,
                            selectedLabelColor = Color.White
                        ),
                        border = BorderStroke(width = 1.dp, color = iconColor),
                        modifier = Modifier,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }

            // Most Popular Section
            Text(
                text = "Most Popular",
                color = textColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Most Popular",
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "See All",
                    color = iconColor,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Handle see all action */ }
                )
            }
            if (movies.isNotEmpty() && !isLoading) {
                LazyRow(
                    modifier = Modifier.padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(movies.take(3)) { movie ->
                        MovieCard(movie = movie)
                    }
                }
            } else if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp).padding(bottom = 16.dp)) {
                    CircularProgressIndicator(color = iconColor, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .clickable { /* Handle movie click */ },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF252836))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = String.format("%.1f", movie.rating),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}