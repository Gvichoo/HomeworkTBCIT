package com.example.homeworktbc.data.remote.api

import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.remote.response.LoginResponse
import com.example.homeworktbc.data.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/login")
    suspend fun login(@Body loginRequest : AuthRequest): Response<LoginResponse>

    @POST("/api/register")
    suspend fun register(@Body registerRequest: AuthRequest): Response<RegisterResponse>
}