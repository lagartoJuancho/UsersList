package com.jda.randomuser.ui.screens.details

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun StartIcon(icon: Painter, contentDescription: String, modifier: Modifier) {
    Icon(painter = icon, contentDescription = contentDescription, modifier = modifier)
}