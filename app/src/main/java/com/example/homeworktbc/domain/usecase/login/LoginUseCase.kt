package com.example.homeworktbc.domain.usecase.login

import com.example.homeworktbc.domain.core.handleHttpRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.login.LoginResponse
import com.example.homeworktbc.domain.repository.LoginRepository
import com.example.homeworktbc.presentation.mapper.toDomain
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository : LoginRepository) {
    suspend operator fun invoke(email : String,password : String,rememberMe : Boolean) : Resource<LoginResponse>{
        return handleHttpRequest(
            apiCall = { loginRepository.login(email, password,rememberMe) },
            mapToDomain = {  it.toDomain() }
        )
    }
}