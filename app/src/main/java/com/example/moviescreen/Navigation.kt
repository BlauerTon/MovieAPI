package com.example.moviescreen

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CustomBottomNavigation(selectedTab: Int, onTabSelected: (Int) -> Unit, navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF16141F),
        tonalElevation = 0.dp
    ) {
        val items = listOf(
            Icons.Default.Home to "Home",
            Icons.Default.Search to "Search",
            Icons.Default.Download to "Downloads",
            Icons.Default.Person to "Profile"
        )

        items.forEachIndexed { index, (icon, label) ->
            val isSelected = selectedTab == index
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onTabSelected(index)
                    when (label) {
                        "Home" -> navController.navigate("HomeScreen") {
                            popUpTo("HomeScreen") { saveState = true }
                            launchSingleTop = true
                        }
                        "Search" -> navController.navigate("search")
                        "Downloads" -> navController.navigate("downloads")
                        "Profile" -> navController.navigate("profile")
                    }
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .background(
                                if (isSelected) Color(0xFF1E1C2A) else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = label,
                                tint = if (isSelected) Color(0xFF00D8FF) else Color(0xFF6D6C75),
                                modifier = Modifier.size(24.dp)
                            )
                            if (isSelected) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = label,
                                    color = Color(0xFF00D8FF),
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                },
                label = { Text("") },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}