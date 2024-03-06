package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jda.randomuser.ui.R
import com.jda.randomuser.ui.screens.SpaceVertically

@Composable
fun UserListError() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            painter = painterResource(id = R.drawable.ic_warning),
            contentDescription = "warning",
            tint = Color.Yellow
        )

        SpaceVertically(height = 30.dp)

    }
}