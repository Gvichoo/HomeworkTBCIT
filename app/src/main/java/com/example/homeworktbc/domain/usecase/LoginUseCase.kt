package com.example.homeworktbc.domain.usecase

import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.login.LoginResponse
import com.example.homeworktbc.domain.repository.LoginRepository
import com.example.homeworktbc.presentation.mapper.toDomain
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository : LoginRepository) {
    suspend operator fun invoke(email : String,password : String) : Resource<LoginResponse>{
        return handleHttpRequest(
            apiCall = { loginRepository.login(email, password) },
            mapToDomain = {  it.toDomain() }
        )
    }
}