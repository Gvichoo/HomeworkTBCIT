package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.api.StoryApiService
import com.example.homeworktbc.data.model.StoryResponse
import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.StoryRepository
import com.example.homeworktbc.domain.StoryRetrofit
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

@ViewModelScoped
class StoryRepositoryImpl @Inject constructor(
    @StoryRetrofit private val retrofit: Retrofit
) :StoryRepository {
    private val storyApiService = retrofit.create(StoryApiService::class.java)

    override fun getStories(): Flow<Resource<List<StoryResponse>>> = flow{
        emit(Resource.Loading())

        val response = handleHttpRequest { storyApiService.fetchStories() }

        emit(response)
    }
}