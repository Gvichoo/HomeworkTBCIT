package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.login.LoginResponse
import kotlinx.coroutines.flow.Flow


interface LoginRepository {
    suspend fun login(email: String, password: String,rememberMe : Boolean): Flow<Resource<LoginResponse>>
}