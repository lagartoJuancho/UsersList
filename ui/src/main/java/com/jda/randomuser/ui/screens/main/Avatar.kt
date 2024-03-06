package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jda.randomuser.ui.R

@Composable
fun Avatar(modifier: Modifier, image: String) {
    AsyncImage(
        model = image,
        contentDescription = "User Avatar",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(52.dp)
            .clip(CircleShape)
            .border(1.dp, Color.LightGray, CircleShape),
        placeholder = painterResource(id = R.drawable.no_image)
    )
}