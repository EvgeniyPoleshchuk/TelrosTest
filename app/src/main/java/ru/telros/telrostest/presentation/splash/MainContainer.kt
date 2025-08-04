package ru.telros.telrostest.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.telros.telrostest.R
import ru.telros.telrostest.navigation.AppNavHost
import ru.telros.telrostest.navigation.NavigationScreen
import ru.telros.telrostest.navigation.navigationItems
import ru.telros.telrostest.presentation.splash.components.TopBar
import ru.telros.telrostest.theme.Resources.background

@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val startDestination = NavigationScreen.Splash.route
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isSplashScreen = currentRoute == startDestination
    val title = remember { mutableStateOf(R.string.generation) }
    if (isSplashScreen) {
        SplashScreen({
            navController.navigate(NavigationScreen.Generation.route) {
                popUpTo("splash") { inclusive = true }
            }
        }
        )
    } else {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(background.primary),
            topBar = {
                TopBar(title.value)
            },

            bottomBar = {
                NavigationBar(
                    windowInsets = NavigationBarDefaults.windowInsets,
                    containerColor = background.buttonBlue
                ) {
                    navigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedNavigationIndex.intValue == index,
                            onClick = {
                                selectedNavigationIndex.intValue = index
                                navController.navigate(item.route)
                                title.value = item.name
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = stringResource(item.name),
                                    tint = background.textColor
                                )
                            },
                            label = {
                                Text(
                                    stringResource(id = item.name),
                                    color = background.textColor
                                )
                            }
                        )

                    }
                }
            },
        ) {
            AppNavHost(
                Modifier
                    .padding(it)
                    .background(background.primary),
                navController = navController,
                startDestination = startDestination
            )
        }
    }
}