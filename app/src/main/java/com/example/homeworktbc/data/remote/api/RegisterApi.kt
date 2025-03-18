package com.example.homeworktbc.data.remote.api

import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.remote.response.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("/api/register")
    suspend fun register(@Body registerRequest: AuthRequest): Response<RegisterResponseDto>
}