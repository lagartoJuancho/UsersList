package com.jda.randomuser.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jda.randomuser.data.local.RandomUserDatabase
import com.jda.randomuser.data.local.asDomainModel
import com.jda.randomuser.data.paging.PermanentStorage
import com.jda.randomuser.data.paging.RandomUsersMediator
import com.jda.randomuser.data.remote.UserApiService
import com.jda.randomuser.data.utils.ITEMS_PER_PAGE
import com.jda.randomuser.domain.model.RandomUser
import com.jda.randomuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApiService,
    private val permanentStorage: PermanentStorage
) : UserRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<RandomUser>> {

        val pagingSourceFactory = { permanentStorage.getAllUsers() }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = RandomUsersMediator(
                randoUserApi = api,
                permanentStorage = permanentStorage
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.asDomainModel()
            }
        }
    }

    override fun searchUsers(searchQuery: String?): Flow<List<RandomUser>> {
       return permanentStorage.searchUsers(searchQuery).map { userList ->
           userList.map { user ->
               user.asDomainModel()
           }
       }
    }
}
