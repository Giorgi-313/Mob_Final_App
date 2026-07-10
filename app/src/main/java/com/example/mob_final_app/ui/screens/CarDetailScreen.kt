package com.example.mob_final_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob_final_app.R
import com.example.mob_final_app.data.model.Car
import com.example.mob_final_app.ui.theme.AppGradient
import com.example.mob_final_app.viewmodel.CarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailScreen(
    carId: Int,
    viewModel: CarViewModel,
    onBackClick: () -> Unit
) {
    var car by remember { mutableStateOf<Car?>(null) }

    LaunchedEffect(carId) {
        car = viewModel.getCarById(carId)
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
        car?.let { carDetails ->
            val context = LocalContext.current
            val imageResId = remember(carDetails.imageUrl) {
                if (carDetails.imageUrl.isNotEmpty()) {
                    context.resources.getIdentifier(carDetails.imageUrl, "drawable", context.packageName)
                } else 0
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "${carDetails.brand} ${carDetails.model}",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (imageResId != 0) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = "${carDetails.brand} ${carDetails.model}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // Fallback image in details
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White.copy(alpha = 0.05f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Placeholder",
                            modifier = Modifier.size(100.dp),
                            alpha = 0.2f,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                DetailItem(label = "Category", value = carDetails.category)
                DetailItem(label = "Fuel Efficiency", value = carDetails.fuelEfficiency)
                DetailItem(label = "Brand", value = carDetails.brand)
                DetailItem(label = "Model", value = carDetails.model)
                DetailItem(label = "Year", value = carDetails.year.toString())
                DetailItem(label = "Engine", value = carDetails.engine)
                DetailItem(label = "Horsepower", value = "${carDetails.horsepower} hp")
                DetailItem(label = "Transmission", value = carDetails.transmission)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Description",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = carDetails.description,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold, color = Color.White)
        Text(text = value, color = Color.White)
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), thickness = 0.5.dp, color = Color.White.copy(alpha = 0.2f))
}
