package com.jda.randomuser.ui.screens.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.jda.randomuser.ui.R
import com.jda.randomuser.ui.toDecodedString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    modifier: Modifier,
    name: String,
    email: String,
    gender: String,
    date: String,
    phone: String,
    latitude: String,
    longitude: String,
    properties: MapProperties,
    uiSettings: MapUiSettings,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InfoItem(
            modifier = Modifier,
            title = "Nombre y Apellidos",
            description = name,
            icon = painterResource(id = R.drawable.ic_profile_user)
        )

        InfoItem(
            modifier = Modifier,
            title = "Email",
            description = email,
            icon = painterResource(id = R.drawable.ic_mailbox)
        )

        InfoItem(
            modifier = Modifier,
            title = "Género",
            description = gender,
            icon = painterResource(id = R.drawable.ic_gender)
        )

        InfoItem(
            modifier = Modifier,
            title = "Fecha de registro",
            description = date.toDecodedString(),
            icon = painterResource(id = R.drawable.ic_calendar)
        )

        InfoItem(
            modifier = Modifier,
            title = "Telefono",
            description = phone,
            icon = painterResource(id = R.drawable.ic_phone)
        )

        Address(
            modifier = Modifier,
            latitude = latitude,
            longitude = longitude,
            name = name,
            properties = properties,
            uiSettings = uiSettings
        )
    }
}
