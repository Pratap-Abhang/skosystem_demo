package com.abhang.skosystem_demo.data.local.repository

import com.abhang.skosystem_demo.data.local.dao.UserDao
import com.abhang.skosystem_demo.domain.models.UserData
import com.abhang.skosystem_demo.domain.repository.UserDataLocalRepository
import javax.inject.Inject

class UserDataLocalRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserDataLocalRepository {
    override suspend fun addUser(userData: UserData) {
        userDao.addUser(userData)
    }

    override suspend fun addUsers(userData: List<UserData>) {
        userDao.addUsers(userData)
    }

    override suspend fun getUserData(limit: Int, offset: Int): List<UserData> {
        return userDao.getData(limit, offset)
    }

    override suspend fun getUserCount(id:Int): Int {
        return userDao.getUserCount(id)
    }

    override suspend fun checkIfUserExist(ids: List<Int>): List<Int> {
        return userDao.checkIfUserExist(ids)
    }

}