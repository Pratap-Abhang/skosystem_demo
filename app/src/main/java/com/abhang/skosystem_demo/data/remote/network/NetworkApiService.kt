package com.abhang.skosystem_demo.data.remote.network

import com.abhang.skosystem_demo.data.data_soure.user_data_dto.UserDataDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiService {

    @GET("users")
    suspend fun getUserData(@Query("page") page: String) : UserDataDTO
}