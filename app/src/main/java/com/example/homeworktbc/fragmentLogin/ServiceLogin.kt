package com.example.homeworktbc.fragmentLogin

import com.example.homeworktbc.RetrofitObject
import com.example.homeworktbc.models.AuthRequest
import retrofit2.Response

class ServiceLogin {
    suspend fun login(authRequest: AuthRequest): Response<LoginResponse> {
        return RetrofitObject.retrofit.login(authRequest)
    }
}
