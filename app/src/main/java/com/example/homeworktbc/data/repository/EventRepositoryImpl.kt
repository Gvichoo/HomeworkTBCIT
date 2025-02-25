package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.api.EventApiService
import com.example.homeworktbc.data.model.Event
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.di.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventApiService: EventApiService
) : EventRepository {

    override suspend fun getEvents(): Flow<Resource<List<Event>>> = flow {
        emit(Resource.Loading())

        val response = handleHttpRequest { eventApiService.getEvents() }

        emit(response)


    }
}