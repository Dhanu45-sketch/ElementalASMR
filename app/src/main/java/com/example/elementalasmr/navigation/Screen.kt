

package com.example.elementalasmr.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Library : Screen("library")
    object Player : Screen("player/{soundId}") {
        fun createRoute(soundId: String) = "player/$soundId"
    }
    object Meditation : Screen("meditation")
    object Profile : Screen("profile")
}