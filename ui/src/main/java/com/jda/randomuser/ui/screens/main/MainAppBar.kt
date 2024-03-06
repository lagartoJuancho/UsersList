package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.jda.randomuser.ui.R
import com.jda.randomuser.ui.theme.Oswald

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(color: Color = Color.Black, title: String, onBackPressed: () -> Unit, onSearchClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = Oswald,
                fontWeight = FontWeight.Normal,
                color = color
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "back",
                    tint = color
                )
            }
        },
        actions = {
            AppBarAction(
                resource = painterResource(id = R.drawable.ic_more_options),
                onMoreOptionsClick = { /*TODO*/ },
                onSearchClick = onSearchClick,
                contentDescription = "more options",
                color = color
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
private fun AppBarAction(
    resource: Painter,
    onMoreOptionsClick: () -> Unit,
    onSearchClick: () -> Unit,
    contentDescription: String,
    color: Color
) {
    IconButton(onClick = onSearchClick) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
    }
    IconButton(onClick = onMoreOptionsClick) {
        Icon(
            painter = resource,
            contentDescription = contentDescription,
            tint = color)
    }
}