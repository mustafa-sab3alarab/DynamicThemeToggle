package com.pearl.team.dynamicthemetoggle.ui.screens.home

import ImageSlider
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    initBackgroundSize: Int = 40,
) {

    val navController = LocalNavController.current
    val screenSize = getScreenSize(LocalConfiguration.current)

    BackgroundAnimation(
        backgroundSize = initBackgroundSize,
        screenSize = screenSize.second,
        darkTheme = darkTheme,
        onThemeUpdated = onThemeUpdated,
    ) {
        HomeContent(
            onGoDetails = { navController.toDetails() },
        )
    }
}

@Composable
fun BackgroundAnimation(
    backgroundSize: Int,
    screenSize: Dp,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    content: @Composable () -> Unit
) {

    val backgroundSizeAnimation by animateDpAsState(
        targetValue = if (darkTheme) screenSize + 64.dp else 0.dp,
        label = "",
        animationSpec = tween(300)
    )

    val cornerShapeAnimation by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else 50.dp,
        label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {

            Box(
                Modifier
                    .width(backgroundSizeAnimation * 2)
                    .height(backgroundSizeAnimation)
                    .clip(RoundedCornerShape(cornerShapeAnimation))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )

            ThemeSwitcher(modifier = Modifier.padding(32.dp), size = 40.dp, darkTheme = darkTheme) { onThemeUpdated() }

        }

        content()
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


private fun getScreenSize(configuration: Configuration): Pair<Dp, Dp> {
    return Pair(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
}
