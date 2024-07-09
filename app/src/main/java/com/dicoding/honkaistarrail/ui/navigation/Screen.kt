package com.dicoding.honkaistarrail.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{characterId}") {
        fun createRoute(characterId: String) = "home/$characterId"
    }
    object About : Screen("about")
}
