package com.vicompose.data.network.service

import com.vicompose.data.network.dto.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SerperApiService {

    @GET("/images?hl=ru&gl=ru&num=10")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int
    ): ApiResponse
}