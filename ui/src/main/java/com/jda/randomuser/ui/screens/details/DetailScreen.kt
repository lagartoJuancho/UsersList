package com.jda.randomuser.ui.screens.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jda.randomuser.ui.screens.main.MainAppBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    onBackPressed: () -> Unit,
    photo: String,
    name: String,
    email: String,
    gender: String,
    date: String,
    phone: String,
    latitude: String,
    longitude: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        UserProfile(
            photo = photo,
            name = name,
            email = email,
            gender = gender,
            date = date,
            phone = phone,
            latitude = latitude,
            longitude = longitude,
            properties = state.properties,
            uiSettings = state.uiSettings
        )
        MainAppBar(color = Color.White, title = name, onBackPressed = onBackPressed, onSearchClick = {})
    }
}

