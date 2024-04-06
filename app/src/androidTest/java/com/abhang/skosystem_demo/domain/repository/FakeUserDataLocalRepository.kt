package com.abhang.skosystem_demo.domain.repository

import com.abhang.skosystem_demo.domain.models.UserData

class FakeUserDataLocalRepository: UserDataLocalRepository {

    private fun getData(): List<UserData> {
        val mList = ArrayList<UserData>()
        for (i in 0..5) {
            val user = UserData(
                userId = i + 1,
                firstName = "Pratap $i",
                lastName = "Abhang $i",
                email = "pratapabhangg1@gmail.com $i",
                avatar = "demo $i",
            )
            mList.add(user)
        }
        return mList
    }

    override suspend fun addUser(userData: UserData) {

    }

    override suspend fun addUsers(userData: List<UserData>) {

    }

    override suspend fun getUserData(limit: Int, offset: Int): List<UserData> {
        return getData()
    }

    override suspend fun getUserCount(id: Int): Int {
        return getData().count()
    }

    override suspend fun checkIfUserExist(ids: List<Int>): List<Int> {
        return getData().map { it.userId }
    }
}