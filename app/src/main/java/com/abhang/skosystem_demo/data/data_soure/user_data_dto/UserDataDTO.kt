package com.abhang.skosystem_demo.data.data_soure.user_data_dto

data class UserDataDTO(
    val data: List<UserDTO>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)