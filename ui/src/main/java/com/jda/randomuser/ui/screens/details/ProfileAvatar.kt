package com.jda.randomuser.ui.screens.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import com.jda.randomuser.ui.toDecodedString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileAvatar(modifier: Modifier, photo: String) {
    AsyncImage(
        model = photo.toDecodedString(),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(77.dp)
            .clip(CircleShape)
            .border(3.dp, Color.White, CircleShape)
            .background(Color.LightGray),
        placeholder = painterResource(id = R.drawable.no_image)
    )
}