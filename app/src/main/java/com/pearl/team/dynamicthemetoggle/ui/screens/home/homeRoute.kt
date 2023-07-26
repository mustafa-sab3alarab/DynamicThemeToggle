package com.donat.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pearl.team.dynamicthemetoggle.ui.screens.home.HomeScreen


private const  val  ROUTE ="home"

fun NavGraphBuilder.homeScreenRoute(
    darkTheme :Boolean = false,
    onThemeUpdated: () -> Unit,
){

    composable(route= ROUTE) { HomeScreen(
        darkTheme = darkTheme,
        onThemeUpdated = { onThemeUpdated() }
    ) }
}