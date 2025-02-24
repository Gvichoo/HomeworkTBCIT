package com.example.homeworktbc.data.api

import com.example.homeworktbc.data.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostApiService {
    @GET("1ba8b612-8391-41e5-8560-98e4a48decc7")
    suspend fun fetchPosts() : Response<List<PostResponse>>
}