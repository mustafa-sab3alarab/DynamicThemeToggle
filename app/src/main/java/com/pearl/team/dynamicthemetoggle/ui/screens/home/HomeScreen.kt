package com.pearl.team.dynamicthemetoggle.ui.screens.home

import ImageSlider
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.donat.ui.screens.details.toDetails
import com.fighter.animatedswitch.ThemeSwitcher
import com.pearl.team.dynamicthemetoggle.LocalNavController
import com.pearl.team.dynamicthemetoggle.R
import com.pearl.team.dynamicthemetoggle.ui.theme.Typography
import com.pearl.team.dynamicthemetoggle.ui.utils.Constants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    darkTheme: Boolean = false,
    onThemeUpdated: () -> Unit,
) {

    val navController = LocalNavController.current

    BackgroundAnimation(
        darkTheme = darkTheme,
        onThemeUpdated = onThemeUpdated,
    ) {
        HomeContent(
            onGoDetails = { navController.toDetails() },
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BackgroundAnimation(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    content: @Composable () -> Unit
) {

    var animationOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    AnimatedContent(
        transitionSpec = {
            fadeIn(
                initialAlpha = 0f,
                animationSpec = tween(100)
            ) togetherWith fadeOut(
                targetAlpha = .9f,
                animationSpec = tween(800)
            ) + scaleOut(
                targetScale = 1f,
                animationSpec = tween(800)
            )
        },
        targetState = darkTheme,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        label = "",
    ) { currentTheme ->
        val revealSize = remember { Animatable(1f) }
        LaunchedEffect(key1 = "reveal", block = {
            if (animationOffset.x > 0f) {
                revealSize.snapTo(0f)
                revealSize.animateTo(1f, animationSpec = tween(800))
            } else {
                revealSize.snapTo(1f)
            }
        })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CirclePath(revealSize.value, animationOffset))
        ) {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color =  MaterialTheme.colorScheme.secondaryContainer
            ) {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), contentAlignment = Alignment.TopCenter) {
                    ThemeSwitcher(
                        size = 40.dp,
                        darkTheme = currentTheme
                    ) {
                        animationOffset = it
                        onThemeUpdated()
                    }
                    content()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    onGoDetails: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(top = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageSlider(imageList = Constants.imageList,
            pagerState = rememberPagerState(
                initialPage = 0,
                initialPageOffsetFraction = 0f
            ) {
                Constants.imageList.size
            }, onClick = {
                onGoDetails()
            })

        Text(
            text = stringResource(R.string.foodeasy),
            style = Typography.titleLarge.copy(color = MaterialTheme.colorScheme.tertiary),
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = stringResource(R.string.as_the_rich_aroma_of_freshly_baked_chocolate_chip_cookies_fills_the_air_all_worries_melt_away_and_a_warm_hug_from_the_oven_embraces_your_soul_with_sheer_delight),
            style = Typography.labelLarge.copy(color = MaterialTheme.colorScheme.tertiary),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}
