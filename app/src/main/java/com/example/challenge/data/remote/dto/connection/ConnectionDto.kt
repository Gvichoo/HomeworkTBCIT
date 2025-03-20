package com.example.challenge.data.remote.dto.connection

import com.squareup.moshi.Json

data class ConnectionDto(
    val id: Int,
    val avatar: String,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String
)