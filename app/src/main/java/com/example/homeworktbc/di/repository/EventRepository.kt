package com.example.homeworktbc.di.repository

import com.example.homeworktbc.data.model.Event
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.presentation.event.event.EventEvent
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvents(): Flow<Resource<List<Event>>>
}