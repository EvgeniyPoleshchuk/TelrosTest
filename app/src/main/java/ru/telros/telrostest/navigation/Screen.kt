package ru.telros.telrostest.navigation

import ru.telros.telrostest.R

enum class Screen {
    SPLASH, GENERATION, DICTIONARY
}

sealed class NavigationScreen(val route: String) {
    object Splash : NavigationScreen(Screen.SPLASH.name)
    object Generation : NavigationScreen(Screen.GENERATION.name)
    object Dictionary : NavigationScreen(Screen.DICTIONARY.name)

}

data class NavigationItem(
    val name: Int,
    val icon: Int,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        name = R.string.generation,
        icon = R.drawable.baseline_password_24,
        route = NavigationScreen.Generation.route
    ),
    NavigationItem(
        name = R.string.dictionary,
        icon = R.drawable.baseline_local_library_24,
        route = NavigationScreen.Dictionary.route
    )
)
