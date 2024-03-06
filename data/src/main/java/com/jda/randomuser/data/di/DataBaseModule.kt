package com.jda.randomuser.data.di

import android.content.Context
import androidx.room.Room
import com.jda.randomuser.data.local.RandomUserDatabase
import com.jda.randomuser.data.utils.RANDOM_USER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): RandomUserDatabase {
        return Room.databaseBuilder(
            context,
            RandomUserDatabase::class.java,
            RANDOM_USER_DATABASE
        ).build()
    }
}
