package com.jda.randomuser.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jda.randomuser.data.local.RandomUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomUserDao {

    @Query("SELECT * FROM random_user_table")
    fun getAllUsers(): PagingSource<Int, RandomUserEntity>

    @Query("SELECT * FROM random_user_table WHERE displayName LIKE :searchQuery|| '%'")
    fun searchUsers(searchQuery: String?): Flow<List<RandomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(users: List<RandomUserEntity>)

    @Query("DELETE FROM random_user_table")
    suspend fun deleteAllUsers()
}
