package com.example.mob_final_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mob_final_app.data.local.AppDatabase
import com.example.mob_final_app.data.repository.CarRepository
import com.example.mob_final_app.ui.navigation.NavGraph
import com.example.mob_final_app.ui.theme.Mob_Final_AppTheme
import com.example.mob_final_app.viewmodel.CarViewModel
import com.example.mob_final_app.viewmodel.CarViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Database and Repository
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = CarRepository(database.carDao())
        val factory = CarViewModelFactory(repository)
        
        enableEdgeToEdge()
        setContent {
            Mob_Final_AppTheme {
                val navController = rememberNavController()
                val viewModel: CarViewModel = viewModel(factory = factory)
                
                NavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}
