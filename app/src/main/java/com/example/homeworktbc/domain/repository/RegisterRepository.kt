package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.remote.response.RegisterResponseDto
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RegisterRepository {
    suspend fun register(authRequest: AuthRequest): Flow<Resource<RegisterResponse>>
}