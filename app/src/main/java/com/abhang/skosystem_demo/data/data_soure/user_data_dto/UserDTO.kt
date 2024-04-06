package com.abhang.skosystem_demo.data.data_soure.user_data_dto

import com.abhang.skosystem_demo.domain.models.UserData

data class UserDTO(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
) {
    fun toUserData(): UserData {
        return UserData(
            userId= id,
            firstName= first_name,
            lastName= last_name,
            email= email,
            avatar= avatar,
        )
    }

}