package com.example.elementalasmr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.elementalasmr.screens.*
import com.example.elementalasmr.navigation.*
import com.example.elementalasmr.ui.theme.ElementalASMRTheme

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Login.route

) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onElementClick = { element ->
                    navController.navigate(Screen.Library.route)
                },
                onSoundClick = { sound ->
                    navController.navigate(Screen.Player.createRoute(sound.id))
                }
            )
        }

        composable(Screen.Library.route) {
            LibraryScreen(
                onSoundClick = { sound ->
                    navController.navigate(Screen.Player.createRoute(sound.id))
                }
            )
        }

        composable(
            route = Screen.Player.route,
            arguments = listOf(navArgument("soundId") { type = NavType.StringType })
        ) { backStackEntry ->
            val soundId = backStackEntry.arguments?.getString("soundId") ?: ""
            PlayerScreen(
                soundId = soundId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Meditation.route) {
            MeditationScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
