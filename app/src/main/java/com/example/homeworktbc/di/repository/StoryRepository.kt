package com.example.homeworktbc.di.repository

import com.example.homeworktbc.data.model.StoryResponse
import com.example.homeworktbc.data.resource.Resource
import kotlinx.coroutines.flow.Flow


interface StoryRepository {
    fun getStories(): Flow<Resource<List<StoryResponse>>>
}


