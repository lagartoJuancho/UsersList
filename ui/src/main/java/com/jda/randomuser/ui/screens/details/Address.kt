package com.jda.randomuser.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.jda.randomuser.ui.screens.SpaceVertically

@Composable
fun Address(
    modifier: Modifier,
    latitude: String,
    longitude: String,
    name: String,
    properties: MapProperties,
    uiSettings: MapUiSettings
) {
    Column(
        modifier = modifier
            .padding(start = 52.dp, top = 5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Dirección",
            fontSize = 11.sp,
            color = Color(0xFF707070),
            fontWeight = FontWeight.Normal
        )
        SpaceVertically(height = 11.dp)

        MapLocation(
            marker = LatLng(latitude.toDouble(), longitude.toDouble()),
            name = name,
            properties = properties,
            uiSettings = uiSettings
        )
    }
}
