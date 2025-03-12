package com.example.homeworktbc.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val id: Int,
    val token: String?
)