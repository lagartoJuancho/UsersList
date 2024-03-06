package com.jda.randomuser.data.paging

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.jda.randomuser.data.local.RandomUserDatabase
import com.jda.randomuser.data.local.RandomUserEntity
import com.jda.randomuser.data.local.RandomUserMetadata
import com.jda.randomuser.data.local.dao.RandomUserDao
import com.jda.randomuser.data.local.dao.MetadataDao
import kotlinx.coroutines.flow.Flow

interface PermanentStorage {
    suspend fun clear()
    suspend fun add(users: List<RandomUserEntity>, keys: List<RandomUserMetadata>)
    suspend fun getMetadata(id: String): RandomUserMetadata?
    fun getAllUsers(): PagingSource<Int, RandomUserEntity>
    fun searchUsers(query: String?): Flow<List<RandomUserEntity>>
}

class PermanentStorageImpl(private val roomDatabase: RandomUserDatabase) : PermanentStorage {
    private val randomUserDao: RandomUserDao = roomDatabase.randomUserDao()
    private val metadataDao: MetadataDao =
        roomDatabase.metadataDao()

    override suspend fun clear() {
        roomDatabase.withTransaction {
            randomUserDao.deleteAllUsers()
            metadataDao.deleteMetadata()
        }
    }

    override suspend fun add(
        users: List<RandomUserEntity>,
        keys: List<RandomUserMetadata>
    ) {
        roomDatabase.withTransaction {
            randomUserDao.addUsers(users)
            metadataDao.addAllMetadata(keys)
        }
    }

    override suspend fun getMetadata(id: String) = metadataDao.getMetadata(id)

    override fun getAllUsers() = randomUserDao.getAllUsers()

    override fun searchUsers(query: String?) = randomUserDao.searchUsers(query)
}
