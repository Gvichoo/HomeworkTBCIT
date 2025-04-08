package com.example.homeworktbc.presentation.mapper

import com.example.homeworktbc.data.response.RegisterResponseDto
import com.example.homeworktbc.domain.model.RegisterResponse

fun RegisterResponseDto.toDomain(): RegisterResponse {
    return RegisterResponse(
        id = this.id,
        token = this.token
    )
}