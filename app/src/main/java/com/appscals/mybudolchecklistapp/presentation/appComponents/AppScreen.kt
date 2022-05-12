package com.appscals.mybudolchecklistapp.presentation.appComponents

sealed class AppScreen(val route: String) {
    object SplashScreen : AppScreen("splash_screen")
    object MainScreen : AppScreen("main_screen")
    object AddItemScreen : AppScreen("edit?itemId={itemID}") {
        fun passId(itemID: Int?): String {
            return "edit?itemId=$itemID"
        }
    }
}
