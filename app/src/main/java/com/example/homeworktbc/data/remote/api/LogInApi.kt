package com.example.homeworktbc.data.remote.api

import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.remote.response.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInApi {
    @POST("/api/login")
    suspend fun login(@Body loginRequest: AuthRequest): Response<LoginResponseDto>
}