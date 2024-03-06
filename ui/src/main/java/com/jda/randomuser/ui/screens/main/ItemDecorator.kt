package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ItemDecorator(modifier: Modifier, padding: Dp = 16.dp) {
    HorizontalDivider(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth()
            .padding(start = padding),
        color = Color(0xFFE7E7E7)
    )
}