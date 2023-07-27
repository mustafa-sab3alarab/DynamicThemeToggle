package com.pearl.team.dynamicthemetoggle.ui.Composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fighter.animatedswitch.ThemeSwitcher
import com.pearl.team.dynamicthemetoggle.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    title:String ="",
    darkTheme:Boolean,
    onThemeUpdated:()->Unit
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.titleLarge.copy(color =  MaterialTheme.colorScheme.tertiary),
            )
        },
        navigationIcon = {

        },
        actions = {
            ThemeSwitcher(darkTheme = darkTheme, size = 30.dp) {
                onThemeUpdated()
            }
        },
        colors= TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.padding(horizontal = 8.dp),
    )
}