package com.example.homeworktbc.paging.retro

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicePage {
    @GET("users")
    suspend fun getUsers(@Query("page") page : Int): Response<PageData>
}