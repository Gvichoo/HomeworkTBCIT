package com.example.homeworktbc.fragmentRegister

import com.example.homeworktbc.RetrofitObject
import com.example.homeworktbc.models.AuthRequest
import retrofit2.Response

class ServiceRegister {
    suspend fun register(authRequest: AuthRequest) : Response<RegisterResponse> {
        return RetrofitObject.retrofit.register(authRequest)
    }
}