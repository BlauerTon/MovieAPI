package com.example.moviescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color(0xFF1f1d2b))
                    .padding(8.dp)
            ) {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchChanged = { searchQuery = it },
                    onCancelSearch = { searchQuery = TextFieldValue("") }  // Added functionality to clear the search
                )
            }
        },
        containerColor = Color(0xFF1f1d2b)
    ) { paddingValues ->
        MovieList(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun SearchBar(searchQuery: TextFieldValue, onSearchChanged: (TextFieldValue) -> Unit, onCancelSearch: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)  // Ensures the search bar doesn't stretch all the way to the right
                .background(Color(0xFF292638), RoundedCornerShape(25.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = searchQuery,
                    onValueChange = onSearchChanged,
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 16.sp),
                    decorationBox = { innerTextField ->
                        if (searchQuery.text.isEmpty()) {
                            Text("Search", color = Color.Gray, fontSize = 16.sp)
                        }
                        innerTextField()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(
            onClick = onCancelSearch,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Cancel", color = Color(0xFF26C6DA))  // Cancel button with custom color
        }
    }
}

@Composable
fun MovieList(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        MovieItem(
            title = "Riverdale",
            year = "2021",
            duration = "148 Minutes",
            tag = "Free",
            rating = "4.5",
            consumerTag = "PG-13",
            category = "Action",
            type = "Movie",
            imageRes = R.drawable.riverdale
        )
        MovieItem(
            title = "Life of PI",
            year = "2021",
            duration = "148 Minutes",
            tag = "Premium",
            rating = "4.5",
            consumerTag = "PG-13",
            category = "Action",
            type = "Movie",
            imageRes = R.drawable.life_of_pi
        )
    }
}

@Composable
fun MovieItem(
    title: String,
    year: String,
    duration: String,
    tag: String,
    rating: String,
    consumerTag: String,
    category: String,
    type: String,
    imageRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(modifier = Modifier.size(100.dp)) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                //.background(Color.Gray) // Placeholder background color if image fails
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFFFFA000), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                    .align(Alignment.TopStart)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color.White, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = rating, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .background(if (tag == "Free") Color(0xFF27AE60) else Color(0xFFE67E22), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(text = tag, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "Year", tint = Color.Gray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = year, color = Color.Gray, fontSize = 12.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Schedule, contentDescription = "Duration", tint = Color.Gray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = duration, color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(Color(0xFF26C6DA), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(text = consumerTag, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Movie, contentDescription = "Category", tint = Color.Gray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$category | $type", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieAppScreen() {
    MovieAppScreen()
}
