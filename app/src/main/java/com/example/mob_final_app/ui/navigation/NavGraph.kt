package com.example.mob_final_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mob_final_app.ui.screens.CarDetailScreen
import com.example.mob_final_app.ui.screens.CarListScreen
import com.example.mob_final_app.ui.screens.MainMenuScreen
import com.example.mob_final_app.viewmodel.CarViewModel

sealed class Screen(val route: String) {
    object MainMenu : Screen("main_menu")
    object CarList : Screen("car_list")
    object CarDetail : Screen("car_detail/{carId}") {
        fun createRoute(carId: Int) = "car_detail/$carId"
    }
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: CarViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainMenu.route
    ) {
        composable(Screen.MainMenu.route) {
            MainMenuScreen(
                onViewCarsClick = { navController.navigate(Screen.CarList.route) }
            )
        }
        composable(Screen.CarList.route) {
            CarListScreen(
                viewModel = viewModel,
                onCarClick = { carId ->
                    navController.navigate(Screen.CarDetail.createRoute(carId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.CarDetail.route,
            arguments = listOf(navArgument("carId") { type = NavType.IntType })
        ) { backStackEntry ->
            val carId = backStackEntry.arguments?.getInt("carId") ?: 0
            CarDetailScreen(
                carId = carId,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
