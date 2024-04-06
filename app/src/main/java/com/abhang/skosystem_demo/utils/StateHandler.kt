package com.abhang.skosystem_demo.utils

data class StateHandler<T>(
    val isLoading: Boolean = false,
    val data: T?= null,
    val error: String = ""
)