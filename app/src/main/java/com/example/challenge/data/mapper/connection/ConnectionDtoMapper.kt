package com.example.challenge.data.mapper.connection

import com.example.challenge.data.remote.dto.connection.ConnectionDto
import com.example.challenge.domain.model.connection.GetConnection

fun ConnectionDto.toDomain() :GetConnection {
    return GetConnection(
        avatar = avatar,
        email = email,
        id = id,
        fullName = firstName.plus(" ").plus(lastName)
    )
}