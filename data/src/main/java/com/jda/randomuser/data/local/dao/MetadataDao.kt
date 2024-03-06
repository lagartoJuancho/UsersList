package com.jda.randomuser.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jda.randomuser.data.local.RandomUserMetadata
import com.jda.randomuser.data.utils.RANDOM_USER_METADATA_TABLE

@Dao
interface MetadataDao {

    @Query(" SELECT * FROM $RANDOM_USER_METADATA_TABLE WHERE id=:id")
    suspend fun getMetadata(id: String): RandomUserMetadata?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMetadata(keys: List<RandomUserMetadata>)

    @Query("DELETE FROM $RANDOM_USER_METADATA_TABLE")
    suspend fun deleteMetadata()

}
