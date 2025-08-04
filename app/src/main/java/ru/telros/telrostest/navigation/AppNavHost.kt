package ru.telros.telrostest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.telros.telrostest.presentation.dictionary.DictionaryScreen
import ru.telros.telrostest.presentation.generation.GenerationScreen
import ru.telros.telrostest.presentation.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationScreen.Splash.route) {
            SplashScreen({
                navController.navigate(NavigationScreen.Generation.route) {
                    popUpTo("splash") { inclusive = true }
                }
            }
            )
        }
        composable(NavigationScreen.Generation.route) {
            GenerationScreen()
        }
        composable(NavigationScreen.Dictionary.route) {
            DictionaryScreen()

        }
    }
}