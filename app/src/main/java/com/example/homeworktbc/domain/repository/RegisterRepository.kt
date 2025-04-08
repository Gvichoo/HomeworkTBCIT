package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.data.request.AuthRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(authRequest: AuthRequest): Flow<Resource<RegisterResponse>>
}