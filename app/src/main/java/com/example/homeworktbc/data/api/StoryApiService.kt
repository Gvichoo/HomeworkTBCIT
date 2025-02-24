package com.example.homeworktbc.data.api

import com.example.homeworktbc.data.model.StoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface   StoryApiService {
    @GET("00a18030-a8c7-47c4-b0c5-8bff92a29ebf")
    suspend fun fetchStories(): Response<List<StoryResponse>>
}
