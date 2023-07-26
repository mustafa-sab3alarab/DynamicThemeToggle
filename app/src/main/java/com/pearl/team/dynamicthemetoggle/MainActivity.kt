package com.pearl.team.dynamicthemetoggle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pearl.team.dynamicthemetoggle.ui.screens.home.HomeScreen
import com.pearl.team.dynamicthemetoggle.ui.theme.DynamicThemeToggleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val systemUiController = rememberSystemUiController()
            systemUiController.isSystemBarsVisible = false

            var darkTheme by remember { mutableStateOf(false) }

            DynamicThemeToggleTheme(darkTheme = darkTheme) {
                val navController = rememberNavController()
                ThemeNavGraph(navController = navController,darkTheme = darkTheme, onThemeUpdated = { darkTheme = !darkTheme })
            }
        }
    }
}