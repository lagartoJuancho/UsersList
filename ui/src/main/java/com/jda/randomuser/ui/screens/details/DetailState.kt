package com.jda.randomuser.ui.screens.details

import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings


data class DetailState(
    val isLoading: Boolean = false,
    val properties: MapProperties = MapProperties(mapType = MapType.NORMAL),
    val uiSettings: MapUiSettings = MapUiSettings(zoomControlsEnabled = false)
)
