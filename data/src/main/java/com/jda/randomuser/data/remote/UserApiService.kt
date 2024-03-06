package com.jda.randomuser.data.remote

import com.jda.randomuser.data.remote.dto.ResultsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("api")
    suspend fun getUsersList(
        @Query("page") page: Int,
        @Query("results") pageSize: Int,
        @Query("seed") seed: String? = "e9a5cc9233a79d92",
    ): ResultsDTO
}
