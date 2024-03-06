package com.jda.randomuser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jda.randomuser.data.local.dao.RandomUserDao
import com.jda.randomuser.data.local.dao.MetadataDao

@Database(entities = [RandomUserEntity::class, RandomUserMetadata::class], version = 1)
abstract class RandomUserDatabase: RoomDatabase() {

    abstract fun randomUserDao(): RandomUserDao
    abstract fun metadataDao(): MetadataDao

}
