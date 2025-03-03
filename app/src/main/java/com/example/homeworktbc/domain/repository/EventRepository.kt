package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.data.room.entity.EventEntity
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.modele.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvents(): Flow<Resource<List<Event>>>
    suspend fun addEvent(event: EventEntity)
}