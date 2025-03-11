package com.example.homeworktbc.presentation.mapper

import com.example.homeworktbc.data.remote.response.LoginResponseDto
import com.example.homeworktbc.domain.model.login.LoginResponse

fun LoginResponseDto.toDomain(): LoginResponse {
    return LoginResponse(
        token = this.token
    )
}