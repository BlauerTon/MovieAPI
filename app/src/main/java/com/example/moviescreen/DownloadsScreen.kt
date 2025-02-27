package com.example.moviescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DownloadsScreen(navController: NavController) {
    val backgroundColor = Color(0xFF1F1D2B) // Dark background
    val textColor = Color.White
    val iconColor = Color(0xFF12CDD9) // Cyan accent

    Scaffold(
        modifier = Modifier.background(backgroundColor),
        containerColor = backgroundColor,
        bottomBar = {
            CustomBottomNavigation(
                selectedTab = 2, // Set to 2 for "Downloads"
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = "Downloads",
                tint = iconColor,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Downloads",
                color = textColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "No downloaded movies yet. Start downloading your favorite movies!",
                color = Color(0xFFA0A0A0),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDownloadsScreen() {
    val navController = rememberNavController()
    DownloadsScreen(navController = navController)
}