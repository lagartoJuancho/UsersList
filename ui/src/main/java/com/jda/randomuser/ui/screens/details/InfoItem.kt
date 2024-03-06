package com.jda.randomuser.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jda.randomuser.ui.screens.SpaceVertically
import com.jda.randomuser.ui.screens.main.ItemDecorator

@Composable
fun InfoItem(modifier: Modifier, title: String, description: String, icon: Painter) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val (startIcon, userName, itemDecorator) = createRefs()
        val barrier = createEndBarrier(startIcon)

        StartIcon(
            icon = icon,
            contentDescription = "Profile",
            modifier = Modifier.constrainAs(startIcon) {
                start.linkTo(parent.start)
                top.linkTo(userName.top)
                bottom.linkTo(itemDecorator.bottom)
            })
        InfoDetail(
            modifier = Modifier.constrainAs(userName) {
                start.linkTo(barrier)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            },
            title = title,
            description = description
        )
        ItemDecorator(
            modifier = Modifier.constrainAs(itemDecorator) {
                start.linkTo(barrier)
                end.linkTo(parent.end)
                top.linkTo(userName.bottom, margin = 8.dp)
                width = Dimension.fillToConstraints
            },
            padding = 20.dp
        )
    }
}

@Composable
fun InfoDetail(modifier: Modifier, title: String, description: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Text(
            text = title,
            fontSize = 11.sp,
            color = Color(0xFF707070),
            fontWeight = FontWeight.Normal
        )
        SpaceVertically(height = 8.dp)

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        )
    }
}
