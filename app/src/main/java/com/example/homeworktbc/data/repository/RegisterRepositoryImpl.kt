package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.remote.api.MyApi
import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.remote.response.RegisterResponse
import com.example.homeworktbc.domain.repository.RegisterRepository
import retrofit2.Response
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val api : MyApi
) : RegisterRepository {
    override suspend fun register(authRequest: AuthRequest): Response<RegisterResponse> {
        return api.register(authRequest)
    }

}