package com.pearl.team.dynamicthemetoggle

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    darkTheme: Boolean = false,
    onThemeUpdated: () -> Unit,
    backgroundSize: Float = 64f,
) {

    val backgroundSizeAnimation by animateFloatAsState(
        targetValue = if (darkTheme) 1000f else backgroundSize,
        label = "",
        animationSpec = tween(500)
    )

    val cornerShapeAnimation by animateDpAsState(targetValue = if (darkTheme) 0.dp else 50.dp, label = "")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .width(backgroundSizeAnimation.dp * 2)
                .height(backgroundSizeAnimation.dp)
                .clip(RoundedCornerShape(cornerShapeAnimation))
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )

        ThemeSwitcher(darkTheme = darkTheme, size = backgroundSize.dp) {
            onThemeUpdated()
        }
    }
}