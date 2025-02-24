package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.api.PostApiService
import com.example.homeworktbc.data.model.PostResponse
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.di.repository.PostRepository
import com.example.homeworktbc.domain.PostRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    @PostRetrofit private val retrofit: Retrofit
) : PostRepository{

    private val postApiService = retrofit.create(PostApiService::class.java)

    override fun getPosts(): Flow<Resource<List<PostResponse>>> = flow{
        emit(Resource.Loading())

        val response = handleHttpRequest { postApiService.fetchPosts() }

        emit(response)
    }
}