package com.example.homeworktbc.presentation.mapper

import com.example.homeworktbc.data.response.LoginResponseDto
import com.example.homeworktbc.domain.model.LoginResponse

fun LoginResponseDto.toDomain(): LoginResponse {
    return LoginResponse(
        token = this.token
    )
}