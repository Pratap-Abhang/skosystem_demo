package com.abhang.skosystem_demo.domain.repository

import com.abhang.skosystem_demo.data.data_soure.user_data_dto.Support
import com.abhang.skosystem_demo.data.data_soure.user_data_dto.UserDTO
import com.abhang.skosystem_demo.data.data_soure.user_data_dto.UserDataDTO

class FakeUserDataRepository: UserDataRepository {

    private fun getData(): UserDataDTO {
        val mList = ArrayList<UserDTO>()
        for (i in 0..5) {
            val user = UserDTO(
                id = i + 1,
                first_name = "Pratap $i",
                last_name = "Abhang $i",
                email = "pratapabhangg1@gmail.com $i",
                avatar = "demo $i",
            )
            mList.add(user)
        }

        return UserDataDTO(
            data = mList,
            1,
            6,
            Support("", ""),
            12,
            12
        )
    }

    override suspend fun getUserData(page: Int): UserDataDTO {
        return getData()
    }
}