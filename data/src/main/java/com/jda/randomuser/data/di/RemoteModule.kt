package com.jda.randomuser.data.di

import com.jda.randomuser.data.local.RandomUserDatabase
import com.jda.randomuser.data.paging.PermanentStorage
import com.jda.randomuser.data.paging.PermanentStorageImpl
import com.jda.randomuser.data.remote.UserApiService
import com.jda.randomuser.data.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideUserApi(): UserApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }

    @Singleton
    @Provides
    fun providePermanentStorage(randomUserDatabase: RandomUserDatabase): PermanentStorage {
        return PermanentStorageImpl(randomUserDatabase)
    }
}
