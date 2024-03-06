package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.jda.randomuser.ui.R

@Composable
fun IconNavigator(modifier: Modifier, onIconSelected: () -> Unit = { }) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_small),
        contentDescription = "go to details",
        tint = Color.LightGray,
        modifier = modifier.clickable { onIconSelected() }
    )
}