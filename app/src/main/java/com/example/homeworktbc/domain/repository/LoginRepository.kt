package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.data.remote.response.LoginResponse
import retrofit2.Response


interface LoginRepository {
    suspend fun login(email: String, password: String): Response<LoginResponse>
}