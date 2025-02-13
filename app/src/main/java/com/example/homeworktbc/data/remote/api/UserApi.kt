package com.example.homeworktbc.data.remote.api

import com.example.homeworktbc.data.remote.response.PageData
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getUsers(@Query("page") page : Int): PageData
}