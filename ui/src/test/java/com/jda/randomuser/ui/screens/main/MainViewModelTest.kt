package com.jda.randomuser.ui.screens.main

import androidx.paging.PagingData
import com.jda.randomuser.domain.model.RandomUser
import com.jda.randomuser.domain.model.UserLocation
import com.jda.randomuser.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        whenever(userRepository.getUsers()).thenReturn(flowOf(PagingData.from(userList)))
        whenever(userRepository.searchUsers(any())).thenReturn(flowOf(listOf()))
        viewModel = MainViewModel(userRepository)

    }

    @Test
    fun testUiActionsShowSearch() = runTest {
        // GIVEN

        // WHEN
        viewModel.onActions(MainViewModel.UIActions.ShowSearchBar)

        // THEN
        assertEquals(true, viewModel.state.isSearchBarVisible)
    }

    @Test
    fun testUiActionsCloseSearch() = runTest {
        // GIVEN

        // WHEN
        viewModel.onActions(MainViewModel.UIActions.CloseSearchBar)

        // THEN
        assertEquals(false, viewModel.state.isSearchBarVisible)
    }

    @Test
    fun testUiActionsClearSearch() = runTest {
        // GIVEN

        // WHEN
        viewModel.onActions(MainViewModel.UIActions.ClearSearchBar)

        // THEN
        assertEquals("", viewModel.state.searchText)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUiActionsSearchInputChange() = runTest {
        // GIVEN
        val searchQuery2 = "John"
        val searchQuery = flowResult(viewModel._searchQuery)

        // WHEN
        viewModel.onActions(MainViewModel.UIActions.SearchInputChange(searchQuery2))
        advanceUntilIdle()

        // THEN
        assertEquals(searchQuery2, viewModel.state.searchText)
        assertEquals(searchQuery2, searchQuery[1])
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUiActionsGetSearchList() = runTest {
        // GIVEN
        val searchQuery2 = "John"
        val searchQuery = flowResult(viewModel.getSearchResults())
        whenever(userRepository.searchUsers(searchQuery2)).thenReturn(flowOf(filteredUserList))


        // WHEN
        viewModel.onActions(MainViewModel.UIActions.SearchInputChange(searchQuery2))

        advanceUntilIdle()

        // THEN
        assertEquals(filteredUserList, searchQuery[1])
    }


    private val searchQuery = "John"
    private val userList = listOf(
        RandomUser(
            id = "1",
            displayName = "John Doe",
            email = "test@test.com",
            gender = "male",
            dateJoined = "2007-07-09T05:51:59.390Z",
            phone = "613250670",
            location = UserLocation(
                latitude = "-69.8246",
                longitude = "134.8719"
            ),
            photo = "https://randomuser.me/api/portraits/men/75.jpg"
        ),
        RandomUser(
            id = "2",
            displayName = "John Doe",
            email = "test@test.com",
            gender = "male",
            dateJoined = "2007-07-09T05:51:59.390Z",
            phone = "613250670",
            location = UserLocation(
                latitude = "-69.8246",
                longitude = "134.8719"
            ),
            photo = "https://randomuser.me/api/portraits/men/75.jpg"
        ),
        RandomUser(
            id = "2",
            displayName = "Marta Sanchez",
            email = "test@test.com",
            gender = "female",
            dateJoined = "2007-07-09T05:51:59.390Z",
            phone = "613250670",
            location = UserLocation(
                latitude = "-69.8246",
                longitude = "134.8719"
            ),
            photo = "https://randomuser.me/api/portraits/men/75.jpg"
        )
    )

    private val filteredUserList = listOf(
        RandomUser(
            id = "1",
            displayName = "John Doe",
            email = "test@test.com",
            gender = "male",
            dateJoined = "2007-07-09T05:51:59.390Z",
            phone = "613250670",
            location = UserLocation(
                latitude = "-69.8246",
                longitude = "134.8719"
            ),
            photo = "https://randomuser.me/api/portraits/men/75.jpg"
        ),
        RandomUser(
            id = "2",
            displayName = "John Doe",
            email = "test@test.com",
            gender = "male",
            dateJoined = "2007-07-09T05:51:59.390Z",
            phone = "613250670",
            location = UserLocation(
                latitude = "-69.8246",
                longitude = "134.8719"
            ),
            photo = "https://randomuser.me/api/portraits/men/75.jpg"
        )
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
class TestCoroutineRule() : TestWatcher() {

    private val testDispatcher = StandardTestDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> TestScope.flowResult(
    flow: Flow<T>,
    dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(
        TestCoroutineScheduler()
    )
) = mutableListOf<T>().also {
    backgroundScope.launch(dispatcher) {
        flow.toList(it)
    }
}

