package com.pearl.team.dynamicthemetoggle.ui.screens.home

import ImageSlider
import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.donat.ui.screens.details.toDetails
import com.pearl.team.dynamicthemetoggle.LocalNavController
import com.pearl.team.dynamicthemetoggle.R
import com.pearl.team.dynamicthemetoggle.ui.Composable.Appbar
import com.pearl.team.dynamicthemetoggle.ui.theme.Typography
import com.pearl.team.dynamicthemetoggle.ui.utils.Constants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    darkTheme: Boolean = false,
    onThemeUpdated: () -> Unit,
    backgroundSize: Float = 64f,
) {
    val navController=  LocalNavController.current
    val backgroundSizeAnimation by animateFloatAsState(
        targetValue = if (darkTheme) 1000f else backgroundSize,
        label = "",
        animationSpec = tween(500)
    )

    val cornerShapeAnimation by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else 50.dp,
        label = ""
    )
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Appbar(title = "FoodEasy",
                darkTheme=darkTheme,
                onThemeUpdated = {onThemeUpdated()})
        },
    ) {

        homeContent(
            backgroundSizeAnimation = backgroundSizeAnimation,
            cornerShapeAnimation = cornerShapeAnimation,
            onGoDetails = {navController.toDetails()}
        )
    }

}

    @OptIn(ExperimentalFoundationApi::class,)
    @Composable
    fun homeContent(
        backgroundSizeAnimation: Float,
        cornerShapeAnimation: Dp,
        onGoDetails: () -> Unit
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                Modifier
                    .width(backgroundSizeAnimation.dp * 2)
                    .height(backgroundSizeAnimation.dp)
                    .clip(RoundedCornerShape(cornerShapeAnimation))
                    .background(MaterialTheme.colorScheme.background)
            ) {}
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
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

    }
