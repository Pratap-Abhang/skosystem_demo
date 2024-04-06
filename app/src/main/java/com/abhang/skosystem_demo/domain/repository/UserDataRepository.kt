package com.abhang.skosystem_demo.domain.repository

import com.abhang.skosystem_demo.data.data_soure.user_data_dto.UserDataDTO

interface UserDataRepository {

    suspend fun getUserData(page: Int): UserDataDTO

}