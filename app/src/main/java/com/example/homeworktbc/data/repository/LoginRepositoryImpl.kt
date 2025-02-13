package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.remote.api.MyApi
import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.remote.response.LoginResponse
import com.example.homeworktbc.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val api: MyApi
) : LoginRepository {
    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        return api.login(AuthRequest(email, password))
    }
}