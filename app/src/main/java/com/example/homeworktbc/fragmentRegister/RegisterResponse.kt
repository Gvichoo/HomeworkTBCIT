package com.example.homeworktbc.fragmentRegister

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id: Int,
    val token: String?
)