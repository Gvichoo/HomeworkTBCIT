package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.api.RegisterApi
import com.example.homeworktbc.data.request.AuthRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.core.handleHttpRequest
import com.example.homeworktbc.domain.model.RegisterResponse
import com.example.homeworktbc.domain.repository.RegisterRepository
import com.example.homeworktbc.presentation.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RegisterRepositoryImpl @Inject constructor(
    private val api : RegisterApi
) : RegisterRepository {
    override suspend fun register(authRequest: AuthRequest): Flow<Resource<RegisterResponse>> {
        return handleHttpRequest(
            apiCall = { api.register(authRequest) },
            mapToDomain = { it.toDomain() }
        )
    }
}