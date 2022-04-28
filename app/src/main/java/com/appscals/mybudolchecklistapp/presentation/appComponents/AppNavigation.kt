package com.appscals.mybudolchecklistapp.presentation.appComponents

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreenAnimate(navController)
        }
        composable("main_screen") {
            MainScreen(navController)
        }
        composable("addItem") {
            Text(text = "TEST SCREEN")
        }

    }
}