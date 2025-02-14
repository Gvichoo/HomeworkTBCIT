package com.example.homeworktbc.data

import retrofit2.http.GET

interface ApiService {
    @GET("6dffd14a-836f-4566-b024-bd41ace3a874")
    suspend fun getItems(): List<Item>
}

