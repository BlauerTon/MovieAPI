package com.example.moviescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx. navigation. compose. rememberNavController
import kotlinx.coroutines.launch

class Onboarding : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            OnboardingScreen(navController = navController)
        }
    }
}

@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF12121C)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPage(
                imageRes = when (page) {
                    0 -> R.drawable.onboarding1
                    1 -> R.drawable.onboarding2
                    else -> R.drawable.onboarding3
                },
                title = "Lorem ipsum dolor sit amet",
                description = "Semper in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper."
            )
        }

        // Indicator & Next Button Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(pagerState.currentPage)

            NextButton(pagerState.currentPage) {
                scope.launch {
                    val nextPage = pagerState.currentPage + 1
                    if (nextPage < 3) {
                        pagerState.animateScrollToPage(nextPage)
                    } else {
                        // Navigate to MovieListScreen after last onboarding screen
                        navController.navigate("HomeScreen") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PageIndicator(currentPage: Int) {
    Row(
        modifier = Modifier.padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(if (index == currentPage) 20.dp else 8.dp) // Extend active indicator
                    .clip(CircleShape)
                    .background(if (index == currentPage) Color(0xFF00E8E8) else Color.Gray)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
fun NextButton(currentPage: Int, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        // Segment indicator around the button
        repeat(3) { segment ->
            if (segment <= currentPage) {
                Box(
                    modifier = Modifier
                        .size(100.dp + (segment * 20).dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF00E8E8).copy(alpha = 0.3f))
                )
            }
        }

        // Next button
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF00E8E8))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun OnboardingPage(imageRes: Int, title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 24.dp),
            lineHeight = 22.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    val navController = rememberNavController()
    OnboardingScreen(navController = navController)
}
