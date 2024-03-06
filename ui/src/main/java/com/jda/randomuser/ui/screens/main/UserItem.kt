package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jda.randomuser.domain.model.RandomUser

@Composable
fun UserItem(onClick: () -> Unit, user: RandomUser) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .clickable(onClick = onClick)
    ) {
        val (avatar, userInfo, footer, endIcon) = createRefs()
        val barrier = createEndBarrier(avatar)

        Avatar(modifier = Modifier.constrainAs(avatar) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        },
            image = user.photo)

        UserInfo(modifier = Modifier.constrainAs(userInfo) {
            start.linkTo(barrier)
            end.linkTo(endIcon.start, margin = 17.dp)
            top.linkTo(avatar.top)
            width = Dimension.fillToConstraints
        },
            user = user)

        IconNavigator(modifier = Modifier.constrainAs(endIcon) {
            end.linkTo(parent.end, margin = 8.dp)
            top.linkTo(avatar.top)
            bottom.linkTo(avatar.bottom)
        })

        ItemDecorator(modifier = Modifier.constrainAs(footer) {
            start.linkTo(barrier)
            end.linkTo(parent.end)
            top.linkTo(userInfo.bottom, margin = 16.dp)
            width = Dimension.fillToConstraints
        })
    }
}
