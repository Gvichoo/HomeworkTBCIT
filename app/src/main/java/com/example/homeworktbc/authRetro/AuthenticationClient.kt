package com.example.homeworktbc.authRetro

import com.example.homeworktbc.fragmentLogin.LoginResponse
import com.example.homeworktbc.fragmentRegister.RegisterResponse
import retrofit2.Response

class AuthenticationClient {
    suspend fun login(authRequest: AuthRequest): Response<LoginResponse> {
        return RetrofitObject.retrofit.login(authRequest)
    }

    suspend fun register(authRequest: AuthRequest) : Response<RegisterResponse> {
        return RetrofitObject.retrofit.register(authRequest)
    }

}
