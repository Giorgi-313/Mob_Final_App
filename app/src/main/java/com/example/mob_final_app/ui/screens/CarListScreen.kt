package com.example.mob_final_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob_final_app.data.model.Car
import com.example.mob_final_app.ui.theme.AppGradient
import com.example.mob_final_app.viewmodel.CarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarListScreen(
    viewModel: CarViewModel,
    initialCategory: String = "All",
    onCarClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val cars by viewModel.allCars.collectAsState()
    val minHp by viewModel.minHorsepower.collectAsState()
    val selectedCategory by remember { mutableStateOf(initialCategory) }

    val filteredCars = cars.filter { car ->
        (selectedCategory == "All" || car.category == selectedCategory) &&
                car.horsepower >= minHp
    }

    // Pre-populate data if list is empty
    LaunchedEffect(cars) {
        if (cars.isEmpty()) {
            viewModel.prepopulateData()
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradient),
        floatingActionButton = {
            FloatingActionButton(
                onClick = onBackClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = if (selectedCategory == "All") "All Cars" else "$selectedCategory Cars",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 8.dp)
            )

            // Horsepower Filter
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Min Horsepower",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "$minHp HP",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Slider(
                    value = minHp.toFloat(),
                    onValueChange = { viewModel.setMinHorsepower(it.toInt()) },
                    valueRange = 0f..2000f,
                    steps = 20,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = Color.White.copy(alpha = 0.24f)
                    )
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredCars) { car ->
                    CarItem(car = car, onClick = { onCarClick(car.id) })
                }
            }
        }
    }
}

@Composable
fun CarItem(car: Car, onClick: () -> Unit) {
    val context = LocalContext.current
    val imageResId = remember(car.imageUrl) {
        if (car.imageUrl.isNotEmpty()) {
            context.resources.getIdentifier(car.imageUrl, "drawable", context.packageName)
        } else 0
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "${car.brand} ${car.model}",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${car.brand} ${car.model}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Year: ${car.year} | ${car.horsepower} HP",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = car.engine,
                    fontSize = 12.sp,
                    maxLines = 1,
                    color = Color.White
                )
            }
        }
    }
}
