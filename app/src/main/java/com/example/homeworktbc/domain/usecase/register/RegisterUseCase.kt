package com.example.homeworktbc.domain.usecase.register

import com.example.homeworktbc.data.request.AuthRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.RegisterResponse
import com.example.homeworktbc.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend operator fun invoke(authRequest: AuthRequest): Flow<Resource<RegisterResponse>> {
        return registerRepository.register(authRequest)
    }
}