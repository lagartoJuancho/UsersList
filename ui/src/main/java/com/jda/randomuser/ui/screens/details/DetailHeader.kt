package com.jda.randomuser.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jda.randomuser.ui.R

@Composable
fun DetailHeader(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile_bg),
        contentDescription = "header",
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
        contentScale = ContentScale.FillBounds
    )

}
