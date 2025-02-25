package com.example.moviescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx. compose. foundation. verticalScroll
import androidx. compose. foundation. shape. RoundedCornerShape
import androidx.compose.material.icons.filled.Delete
import androidx. compose. ui. graphics. Brush
import androidx. compose. material. icons. filled. Star
import androidx.compose.material.icons.outlined.ChevronRight


@Composable
fun ProfileScreen() {
    val backgroundColor = Color(0xFF1F1D2B)
    val cardColor = Color(0xFF8700)
    val textColor = Color.White
    val iconColor = Color(0xFF12CDD9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()) // Make the screen scrollable
                .padding(16.dp)
        ) {

            // Profile Title
            Text(
                text = "Profile",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Profile Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_image), // Replace with your profile image
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Tiffany",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Text(
                        text = "Tiffanylearsey@gmail.com",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Premium Member Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Premium",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Premium Member",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                            Text(
                                text = "New movies are coming for you,\nDownload Now!",
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Account Settings
            Text(
                text = "Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ProfileMenuItem(icon = Icons.Default.Person, text = "Member")
            ProfileMenuItem(icon = Icons.Default.Security, text = "Change Password")

            Spacer(modifier = Modifier.height(16.dp))

            // General Settings
            Text(
                text = "General",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ProfileMenuItem(icon = Icons.Default.Notifications, text = "Notification")
            ProfileMenuItem(icon = Icons.Default.Language, text = "Language")
            ProfileMenuItem(icon = Icons.Default.LocationOn, text = "Country")
            ProfileMenuItem(icon = Icons.Default.Delete, text = "Clear Cache")

            Spacer(modifier = Modifier.height(16.dp))

            // Legal and Policies
            Text(
                text = "Legal and Policies",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ProfileMenuItem(icon = Icons.Default.Storage, text = "Privacy Policy")
            ProfileMenuItem(icon = Icons.Default.Storage, text = "Terms of Service")

            Spacer(modifier = Modifier.height(16.dp))

            // Help & Feedback
            Text(
                text = "Help & Feedback",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ProfileMenuItem(icon = Icons.Default.Notifications, text = "Help Center")
            ProfileMenuItem(icon = Icons.Default.Language, text = "Send Feedback")

            Spacer(modifier = Modifier.height(16.dp))

            // About Us
            Text(
                text = "About Us",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ProfileMenuItem(icon = Icons.Default.Person, text = "About Company")
            ProfileMenuItem(icon = Icons.Default.LocationOn, text = "Contact Us")

            Spacer(modifier = Modifier.height(16.dp))

            // Log Out Option
            ProfileMenuItem(icon = Icons.Default.Storage, text = "Log Out")
        }
    }
}

@Composable
fun ProfileMenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF12CDD9),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.ChevronRight,  // Use this for a standard right arrow
            contentDescription = "Navigate",
            tint = Color(0xFF12CCD8),
            modifier = Modifier.size(20.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}