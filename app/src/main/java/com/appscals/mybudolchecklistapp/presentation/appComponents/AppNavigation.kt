package com.appscals.mybudolchecklistapp.presentation.appComponents

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appscals.mybudolchecklistapp.presentation.addItem.AddItemScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable(AppScreen.SplashScreen.route) {
            SplashScreenAnimate(navController)
        }
        composable(AppScreen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(AppScreen.AddItemScreen.route) {
            AddItemScreen()
        }

    }
}