package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.jda.randomuser.domain.model.RandomUser
import com.jda.randomuser.ui.pagingLoadStateItem

@Composable
fun UserList(onUserClick: (RandomUser) -> Unit, modifier: Modifier, users: LazyPagingItems<RandomUser>) {
    LazyColumn(
        contentPadding = PaddingValues(top = 35.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
            .background(Color.White)
    ) {
        pagingLoadStateItem(
            loadState = users.loadState.prepend,
            keySuffix = "prepend",
            loading = { UserListLoading() },
            error = { UserListError() },
        )
        items(users.itemCount) {i ->
            val item = users[i]
            if (item != null) {
                UserItem(onClick = { onUserClick(item) }, user = item)
            }
        }
        item {
            UserListLoading()
        }
    }
}
