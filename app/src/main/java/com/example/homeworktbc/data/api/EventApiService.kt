package com.example.homeworktbc.data.api

import com.example.homeworktbc.data.model.Event
import retrofit2.Response
import retrofit2.http.GET

interface EventApiService {
    @GET("event")
    suspend fun getEvents(): Response<List<Event>>
}
