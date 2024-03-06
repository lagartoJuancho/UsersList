package com.jda.randomuser.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jda.randomuser.data.local.RandomUserEntity
import com.jda.randomuser.data.local.RandomUserMetadata
import com.jda.randomuser.data.remote.UserApiService
import com.jda.randomuser.data.remote.dto.ResultDTO
import com.jda.randomuser.data.remote.dto.asEntityModel
import com.jda.randomuser.data.utils.ITEMS_PER_PAGE
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RandomUsersMediator @Inject constructor(
    private val randoUserApi: UserApiService,
    private val permanentStorage: PermanentStorage,
) : RemoteMediator<Int, RandomUserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RandomUserEntity>
    ): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val metadata = getMetadataClosestToCurrentPosition(state)
                    metadata?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val metadata = getMetadataForFirstItem(state)
                    val prevPage = metadata?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = metadata != null
                    )
                    prevPage
                }

                LoadType.APPEND -> {
                    val metadata = getMetadataForLastItem(state)
                    val nextPage = metadata?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = metadata != null
                    )
                    nextPage
                }
            }

            val response = try {
                randoUserApi.getUsersList(page = currentPage, pageSize = ITEMS_PER_PAGE)
            } catch (e: Exception) {
                return MediatorResult.Error(e)
            }
            val endOfPaginationReached = response.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            val keys = response.results.mapNotNull {
                it.toMetadata(
                    prevPage = prevPage,
                    nextPage = nextPage
                )
            }
            if (loadType == LoadType.REFRESH) {
                permanentStorage.clear()
            }
            permanentStorage.add(users = response.results.asEntityModel(), keys = keys)
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getMetadataClosestToCurrentPosition(state: PagingState<Int, RandomUserEntity>): RandomUserMetadata? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                permanentStorage.getMetadata(id = id)
            }
        }
    }

    private suspend fun getMetadataForFirstItem(state: PagingState<Int, RandomUserEntity>): RandomUserMetadata? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { user ->
            permanentStorage.getMetadata(id = user.id)
        }
    }

    private suspend fun getMetadataForLastItem(state: PagingState<Int, RandomUserEntity>): RandomUserMetadata? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { user ->
            permanentStorage.getMetadata(id = user.id)
        }
    }
}

fun ResultDTO.toMetadata(prevPage: Int?, nextPage: Int?) = email?.let {
    RandomUserMetadata(
        id = it,
        prevPage = prevPage,
        nextPage = nextPage
    )
}
