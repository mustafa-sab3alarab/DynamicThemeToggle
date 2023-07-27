package com.pearl.team.dynamicthemetoggle.ui.Composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pearl.team.dynamicthemetoggle.R

@Composable
fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.baseline_close_24),
            contentDescription = "",
            modifier = Modifier
                .size(20.dp)
                .padding(4.dp),
            tint = Color.White
        )
    }


}