package com.abhang.skosystem_demo.domain.repository

import com.abhang.skosystem_demo.domain.models.UserData

interface UserDataLocalRepository {

    suspend fun addUser(userData: UserData)

    suspend fun addUsers(userData: List<UserData>)

    suspend fun getUserData(limit: Int, offset: Int): List<UserData>

    suspend fun getUserCount(id: Int): Int

    suspend fun checkIfUserExist(ids: List<Int>): List<Int>

}