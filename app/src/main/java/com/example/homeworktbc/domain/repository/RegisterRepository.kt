package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.remote.response.RegisterResponse
import retrofit2.Response

interface RegisterRepository {
    suspend fun register(authRequest: AuthRequest): Response<RegisterResponse>
}