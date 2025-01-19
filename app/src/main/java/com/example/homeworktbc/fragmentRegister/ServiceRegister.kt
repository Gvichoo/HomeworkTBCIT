package com.example.homeworktbc.fragmentRegister

import com.example.homeworktbc.RetrofitObject
import com.example.homeworktbc.models.AuthRequest

class ServiceRegister {
    suspend fun register(authRequest: AuthRequest) : RegisterResponse {
        return RetrofitObject.retrofit.register(authRequest)
    }
}