package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jda.randomuser.domain.model.RandomUser

@Composable
fun MainScreen(
    onItemClick: (RandomUser) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val users = viewModel.getUsers.collectAsLazyPagingItems()
    val searchResults: State<List<RandomUser>> = viewModel.getSearchResults().collectAsState(initial = emptyList())

    UsersApp {
        Scaffold(
            modifier = Modifier.background(Color.White),
            topBar = {
                if (state.isSearchBarVisible) {
                    SearchAppBar(
                        onCloseClick = { viewModel.onActions(MainViewModel.UIActions.CloseSearchBar) },
                        searchText = state.searchText,
                        onTextChange = { input ->
                            viewModel.onActions(MainViewModel.UIActions.SearchInputChange(input))
                        },
                        onClearClick = { viewModel.onActions(MainViewModel.UIActions.ClearSearchBar) }
                    )
                } else {
                    MainAppBar(
                        title = "CONTACTOS",
                        onBackPressed = { },
                        onSearchClick = { viewModel.onActions(MainViewModel.UIActions.ShowSearchBar) })
                }
            },
            contentColor = Color.White
        ) { padding ->
            if (state.isSearchBarVisible) {
                SearchUserList(
                    onUserClick = onItemClick, modifier = Modifier.padding(padding), users = searchResults)
            } else {
                UserList(
                    onUserClick = onItemClick,
                    modifier = Modifier.padding(padding),
                    users = users,
                )
            }
        }
    }
}
