package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jda.randomuser.domain.model.RandomUser


@Composable
fun SearchUserList(onUserClick: (RandomUser) -> Unit, modifier: Modifier, users: State<List<RandomUser>>) {
    LazyColumn(
        contentPadding = PaddingValues(top = 35.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.background(Color.White)
    ) {
        items(users.value) { user ->
            UserItem(
                onClick = {  onUserClick(user) },
                user = user
            )
        }
    }
}
