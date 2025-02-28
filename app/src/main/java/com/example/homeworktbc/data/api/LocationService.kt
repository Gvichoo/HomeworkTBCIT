package com.example.homeworktbc.data.api

import retrofit2.Call
import retrofit2.http.GET

interface LocationService {
    @GET("v3/c4c64996-4ed9-4cbc-8986-43c4990d495a")
    fun getLocations(): Call<List<Location>>
}