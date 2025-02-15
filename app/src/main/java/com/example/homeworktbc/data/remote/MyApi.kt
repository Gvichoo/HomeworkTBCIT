package com.example.homeworktbc.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("6dffd14a-836f-4566-b024-bd41ace3a874")
    suspend fun fetchPage(): Response<List<PageData>>
}

