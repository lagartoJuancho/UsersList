package com.jda.randomuser.ui.screens.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jda.randomuser.ui.R

@Composable
fun EditActions(modifier: Modifier) {
    Row(modifier = modifier) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = "camera",
                tint = Color.Black
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "edit",
                tint = Color.Black
            )
        }
    }
}
