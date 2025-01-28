package com.example.homeworktbc.authRetro

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<PageData>
}


