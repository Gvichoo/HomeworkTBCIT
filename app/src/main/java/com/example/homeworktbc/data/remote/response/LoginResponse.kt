package com.example.homeworktbc.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
