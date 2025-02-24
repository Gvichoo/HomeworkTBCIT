package com.example.homeworktbc.di.repository

import com.example.homeworktbc.data.model.PostResponse
import com.example.homeworktbc.data.resource.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts() : Flow<Resource<List<PostResponse>>>
}