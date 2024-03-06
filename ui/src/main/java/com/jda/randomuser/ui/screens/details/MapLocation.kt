package com.jda.randomuser.ui.screens.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jda.randomuser.ui.R

@Composable
fun MapLocation(marker: LatLng, name: String, properties: MapProperties, uiSettings: MapUiSettings) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 21f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = marker),
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ilocation_marker),
            title = "$name Address",
            snippet = "${marker.latitude}, ${marker.longitude}"
        )
    }
}
