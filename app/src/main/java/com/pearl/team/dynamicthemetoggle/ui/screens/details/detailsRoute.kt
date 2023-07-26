package com.donat.ui.screens.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pearl.team.dynamicthemetoggle.ui.screens.details.DetailsScreen

private const  val  ROUTE ="details"

fun NavGraphBuilder.detailsScreenRoute(){
    composable(route= ROUTE) { DetailsScreen() }
}
fun NavController.toDetails(){
    navigate(ROUTE)
}
fun NavController.onBack(){
    navigateUp()
}