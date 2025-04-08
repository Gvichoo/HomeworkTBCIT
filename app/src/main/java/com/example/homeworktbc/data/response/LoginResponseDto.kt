package com.example.homeworktbc.data.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String
)