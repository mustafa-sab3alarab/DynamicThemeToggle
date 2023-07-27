package com.pearl.team.dynamicthemetoggle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pearl.team.dynamicthemetoggle.ui.screens.details.detailsScreenRoute
import com.donat.ui.screens.home.homeScreenRoute

private const val START_DESTINATION ="home"
val LocalNavController = compositionLocalOf<NavHostController> { error("No active user found!") }

@Composable
fun ThemeNavGraph(
    navController: NavHostController,
    darkTheme :Boolean = false,
    onThemeUpdated: () -> Unit,
){

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = START_DESTINATION) {
            homeScreenRoute(
                darkTheme = darkTheme,
                onThemeUpdated = { onThemeUpdated()}
            )
            detailsScreenRoute()
        }
    }
}
