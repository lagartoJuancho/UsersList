package com.jda.randomuser.ui.screens.main

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jda.randomuser.domain.model.RandomUser
import com.jda.randomuser.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val _searchQuery = MutableStateFlow("")

    private var searchJob: Job? = null

    val getUsers: Flow<PagingData<RandomUser>> = repo.getUsers().cachedIn(viewModelScope)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSearchResults() = _searchQuery.flatMapLatest {
        repo.searchUsers(it)
    }

    fun onActions(action: UIActions) {
        when (action) {
            UIActions.ShowSearchBar -> {
                state = state.copy(
                    isSearchBarVisible = true
                )
            }

            UIActions.CloseSearchBar -> {
                state = state.copy(
                    isSearchBarVisible = false,
                )
            }

            is UIActions.SearchInputChange -> {
                state = state.copy(
                    searchText = action.text
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    searchUsers(action.text)
                }
            }

            UIActions.ClearSearchBar -> {
                state = state.copy(
                    searchText = ""
                )
            }
        }
    }

    private fun searchUsers(searchQuery: String) {
        viewModelScope.launch {
            _searchQuery.emit(searchQuery)
        }
    }

    sealed class UIActions {
        data class SearchInputChange(val text: String) : UIActions()
        object ShowSearchBar : UIActions()
        object CloseSearchBar : UIActions()
        object ClearSearchBar : UIActions()
    }
}
