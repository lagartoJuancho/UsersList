package com.jda.randomuser.ui.screens.main

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
import com.jda.randomuser.domain.model.RandomUser
import com.jda.randomuser.ui.screens.SpaceVertically

@Composable
fun UserInfo(modifier: Modifier, user: RandomUser) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Text(
            text = user.displayName,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
        SpaceVertically(height = 8.dp)
        Text(
            text = user.email,
            fontSize = 14.sp,
            color = Color(0xFF8E8E93),
            fontWeight = FontWeight.Medium
        )
    }
}