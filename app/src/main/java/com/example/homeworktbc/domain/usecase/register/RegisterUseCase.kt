package com.example.homeworktbc.domain.usecase.register

import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.core.handleHttpRequest
import com.example.homeworktbc.domain.model.register.RegisterResponse
import com.example.homeworktbc.domain.repository.RegisterRepository
import com.example.homeworktbc.presentation.mapper.toDomain
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend operator fun invoke(authRequest: AuthRequest): Resource<RegisterResponse> {
        return handleHttpRequest(
            apiCall = { registerRepository.register(authRequest) },
            mapToDomain = { it.toDomain() }
        )
    }
}