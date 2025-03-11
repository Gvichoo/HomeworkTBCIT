package com.example.homeworktbc.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.example.homeworktbc.data.remote.response.LoginResponseDto
import retrofit2.Response

interface LoginRepository {
    suspend fun login(email: String, password: String): Response<LoginResponseDto>
}