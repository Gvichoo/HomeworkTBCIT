package com.example.homeworktbc.di.repository

import com.example.homeworktbc.data.model.Event
import com.example.homeworktbc.data.resource.Resource
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvents(): Flow<Resource<List<Event>>>
}