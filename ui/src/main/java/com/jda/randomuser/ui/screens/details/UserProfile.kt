package com.jda.randomuser.ui.screens.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfile(
    photo: String,
    name: String,
    email: String,
    gender: String,
    date: String,
    phone: String,
    latitude: String,
    longitude: String,
    properties: MapProperties,
    uiSettings: MapUiSettings
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (header, avatar, edit, content) = createRefs()
        DetailHeader(modifier = Modifier.constrainAs(header) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        })
        ProfileAvatar(
            modifier = Modifier.constrainAs(avatar) {
                start.linkTo(parent.start, margin = 17.dp)
                top.linkTo(header.bottom)
                bottom.linkTo(header.bottom)
            },
            photo = photo
        )
        EditActions(modifier = Modifier.constrainAs(edit) {
            top.linkTo(header.bottom)
            end.linkTo(parent.end)
        })
        Content(
            modifier = Modifier.constrainAs(content) {
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(avatar.bottom, margin = 12.dp)
                width = Dimension.fillToConstraints
            },
            name = name,
            email = email,
            gender = gender,
            date = date,
            phone = phone,
            latitude = latitude,
            longitude = longitude,
            properties = properties,
            uiSettings = uiSettings
        )
    }
}