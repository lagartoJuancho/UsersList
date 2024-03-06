package com.jda.randomuser.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jda.randomuser.data.KEYS
import com.jda.randomuser.data.PAGES
import com.jda.randomuser.data.getResultDtoList
import com.jda.randomuser.data.getUserEntityList
import com.jda.randomuser.data.remote.UserApiService
import com.jda.randomuser.data.remote.dto.PageInfoDTO
import com.jda.randomuser.data.remote.dto.ResultsDTO
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalPagingApi::class)
class RandomUsersMediatorTest {
    private lateinit var randomUsersMediator: RandomUsersMediator

    @Mock
    private lateinit var userApiService: UserApiService

    @Mock
    private lateinit var permanentStorage: PermanentStorage

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        randomUsersMediator = RandomUsersMediator(userApiService, permanentStorage)
    }

    @Test
    fun `load when loadType is REFRESH should return MediatorResult Success`() = runTest {
        // GIVEN
        val firstPage = 1
        whenever(permanentStorage.getMetadata(any())).thenReturn(KEYS[0][0])

        (1..10).onEach {
            whenever(userApiService.getUsersList(page = eq(it), any(), any())).thenReturn(
                ResultsDTO(
                    info = PageInfoDTO(page = it, results = 10),
                    results = getResultDtoList(it)
                )
            )
        }
        val config = PagingConfig(pageSize = 10)

        // WHEN
        val result = randomUsersMediator.load(
            LoadType.REFRESH,
            PagingState(
                pages = PAGES,
                anchorPosition = 0,
                leadingPlaceholderCount = 0,
                config = config
            )
        )

        // THEN
        verify(permanentStorage).clear()
        verify(permanentStorage).add(getUserEntityList(firstPage), KEYS[firstPage - 1])
        assertTrue(result is RemoteMediator.MediatorResult.Success && !result.endOfPaginationReached)
    }

    @Test
    fun `load when loadType is PREPEND should return MediatorResult Success`() = runTest {
        // GIVEN
        val loadedPages = PAGES.takeLast(7)
        val key = KEYS.takeLast(7)[0][0]
        whenever(permanentStorage.getMetadata(any())).thenReturn(key)

        (1..10).onEach {
            whenever(userApiService.getUsersList(page = eq(it), any(), any())).thenReturn(
                ResultsDTO(
                    info = PageInfoDTO(page = it, results = 10),
                    results = getResultDtoList(it)
                )
            )
        }
        val config = PagingConfig(pageSize = 10)

        // WHEN
        val result = randomUsersMediator.load(
            LoadType.PREPEND,
            PagingState(
                pages = loadedPages,
                anchorPosition = 0,
                leadingPlaceholderCount = 0,
                config = config
            )
        )

        // THEN
        verify(permanentStorage).add(PAGES[2].data, KEYS[2])
        assertTrue(result is RemoteMediator.MediatorResult.Success && !result.endOfPaginationReached)
    }

    @Test
    fun `load when loadType is APPEND should return MediatorResult Success`() = runTest {
        // GIVEN
        val firstPage = 1
        whenever(permanentStorage.getMetadata(any())).thenReturn(KEYS[0][0])

        (1..10).onEach {
            whenever(userApiService.getUsersList(page = eq(it), any(), any())).thenReturn(
                ResultsDTO(
                    info = PageInfoDTO(page = it, results = 10),
                    results = getResultDtoList(it)
                )
            )
        }
        val config = PagingConfig(pageSize = 10)

        // WHEN
        val result = randomUsersMediator.load(
            LoadType.APPEND,
            PagingState(
                pages = PAGES,
                anchorPosition = 0,
                leadingPlaceholderCount = 0,
                config = config
            )
        )

        // THEN
        verify(permanentStorage).add(PAGES[1].data, KEYS[1])
        assertTrue(result is RemoteMediator.MediatorResult.Success && !result.endOfPaginationReached)
    }

    @Test
    fun `load when exception is thrown should return MediatorResult Error`() = runTest {
        // GIVEN
        whenever(permanentStorage.getMetadata(any())).thenReturn(KEYS[0][0])
        whenever(userApiService.getUsersList(page = eq(1), any(), any())).thenThrow(RuntimeException())

        // WHEN
        val result = randomUsersMediator.load(
            LoadType.REFRESH,
            PagingState(
                pages = PAGES,
                anchorPosition = 0,
                leadingPlaceholderCount = 0,
                config = PagingConfig(pageSize = 10)
            )
        )

        // THEN
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }
}
