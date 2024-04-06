package com.abhang.skosystem_demo.data.remote.repository

import com.abhang.skosystem_demo.data.data_soure.user_data_dto.UserDataDTO
import com.abhang.skosystem_demo.data.remote.network.NetworkApiService
import com.abhang.skosystem_demo.domain.repository.UserDataRepository
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApiService
) : UserDataRepository{
    override suspend fun getUserData(page: Int): UserDataDTO {
        return networkApi.getUserData(page.toString())
    }
}