package com.pearl.team.dynamicthemetoggle.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.donat.ui.screens.details.onBack
import com.pearl.team.dynamicthemetoggle.LocalNavController
import com.pearl.team.dynamicthemetoggle.R
import com.pearl.team.dynamicthemetoggle.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(){
    val navController=  LocalNavController.current
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                },
                navigationIcon = {
                                 Icon(painter = painterResource(R.drawable.baseline_arrow_back_24),
                                     contentDescription ="", modifier = Modifier
                                         .padding(4.dp)
                                         .clickable { navController.onBack() },
                                     tint = MaterialTheme.colorScheme.primary)
                },
                colors= TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            )
        },
    ) {
            DetailsContent()
    }

}

@Composable
fun DetailsContent(){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(color = MaterialTheme.colorScheme.background)
            )
        {

                Image(
                    painter = painterResource(id = R.drawable.food_1),
                    contentDescription = stringResource(R.string.cover),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .scale(1.6f)
                        .padding(top = 85.dp),
                )
            Spacer(modifier =Modifier.height(90.dp))
                    Text(text = stringResource(R.string.steak_with_vegetables),
                        style = Typography.titleLarge.copy(color =  MaterialTheme.colorScheme.tertiary),
                        modifier= Modifier.padding(horizontal = 16.dp , vertical = 8.dp),
                        color = MaterialTheme.colorScheme.onBackground)

                    Text(text = stringResource(R.string.introducing_cosmic_bbq_blast_a_mind_blowing_recipe_that_will_send_your_taste_buds_on_an_intergalactic_journey_of_flavors) +
                            "\n" +
                            stringResource(R.string.prepare_to_be_whisked_away_to_a_taste_dimension_beyond_your_wildest_dreams_imagine_succulent_out_of_this_world_bbq_burgers_oozing_with_melting_cheese_topped_with_a_meteor_shower_of_crispy_bacon_bits_and_interstellar_onion_rings)
                          ,
                        style = Typography.labelLarge.copy(color =  MaterialTheme.colorScheme.tertiary),
                        modifier= Modifier.padding(horizontal = 16.dp , vertical = 8.dp),
                        lineHeight = 24.sp)
                }

}




@Preview
@Composable
fun DetailsScreenPrev(){
    DetailsScreen()
}