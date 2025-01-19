package com.example.homeworktbc.fragmentLogin

import com.example.homeworktbc.RetrofitObject
import com.example.homeworktbc.models.AuthRequest

class ServiceLogin {
    suspend fun login(authRequest: AuthRequest): LoginResponse {
        return RetrofitObject.retrofit.login(authRequest)
    }
}
