package com.jda.randomuser.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jda.randomuser.data.utils.RANDOM_USER_METADATA_TABLE

@Entity(tableName = RANDOM_USER_METADATA_TABLE)
data class RandomUserMetadata(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
