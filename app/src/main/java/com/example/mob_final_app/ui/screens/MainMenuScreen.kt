package com.example.mob_final_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob_final_app.R
import com.example.mob_final_app.ui.theme.AppGradient

data class CategoryItem(val name: String, val imageRes: Int)

@Composable
fun MainMenuScreen(onCategoryClick: (String) -> Unit) {
    val categories = listOf(
        CategoryItem("All", R.drawable.nissangtr),
        CategoryItem("Gas", R.drawable.supra),
        CategoryItem("Hybrid", R.drawable.prius),
        CategoryItem("Electric", R.drawable.modelsplaid)
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradient),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Added scroll for smaller screens
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Logo on top of CarSpotters (Tiered look)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "CarSpotters",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(
                text = "Explore the world of performance",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(60.dp))
            
            Text(
                text = "Choose a Category",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            // Using Column for more control over spacing in 1-column layout
            categories.forEach { category ->
                CategoryCard(category = category) {
                    onCategoryClick(category.name)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CategoryCard(category: CategoryItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(12.dp)
            )
            
            // Slightly deeper dark overlay for high contrast text
            Surface(
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxSize()
            ) {}

            Text(
                text = category.name,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}
