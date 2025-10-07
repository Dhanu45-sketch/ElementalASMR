
package com.example.elementalasmr.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem(Screen.Home.route, Icons.Default.Home, "Home")
    object Library : BottomNavItem(Screen.Library.route, Icons.Default.LibraryMusic, "Library")
    object Meditation : BottomNavItem(Screen.Meditation.route, Icons.Default.SelfImprovement, "Meditate")
    object Profile : BottomNavItem(Screen.Profile.route, Icons.Default.Person, "Profile")
}

fun getBottomNavItems(): List<BottomNavItem> = listOf(
    BottomNavItem.Home,
    BottomNavItem.Library,
    BottomNavItem.Meditation,
    BottomNavItem.Profile
)
