package com.example.homeworktbc.domain.usecase.login

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.LoginResponse
import com.example.homeworktbc.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String, rememberMe: Boolean): Flow<Resource<LoginResponse>> {
        return loginRepository.login(email, password, rememberMe)
    }
}