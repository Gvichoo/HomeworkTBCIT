package com.example.homeworktbc.data

import com.bumptech.glide.load.engine.Resource
import com.example.homeworktbc.domain.Location
import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    fun getUsers(): Flow<Resource<List<Location>>>
}