package com.jda.randomuser.domain.repository

import androidx.paging.PagingData
import com.jda.randomuser.domain.model.RandomUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<RandomUser>>
    fun searchUsers(searchQuery: String?): Flow<List<RandomUser>>
}