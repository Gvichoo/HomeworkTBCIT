package com.example.homeworktbc

import com.example.homeworktbc.models.AuthRequest
import com.example.homeworktbc.fragmentLogin.LoginResponse
import com.example.homeworktbc.fragmentRegister.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/login")
    suspend fun login(@Body loginRequest : AuthRequest): Response<LoginResponse>

    @POST("/api/register")
    suspend fun register(@Body registerRequest: AuthRequest): Response<RegisterResponse>

}