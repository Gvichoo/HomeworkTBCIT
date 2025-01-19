package com.example.homeworktbc

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitObject {

    private const val BASE_URL = "https://reqres.in"

    val retrofit  : AuthService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build().create(AuthService::class.java)
}