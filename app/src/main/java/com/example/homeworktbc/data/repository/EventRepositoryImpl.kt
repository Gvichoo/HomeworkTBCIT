package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.api.EventApiService
import com.example.homeworktbc.data.mapper.toDomain
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.data.room.dao.EventDao
import com.example.homeworktbc.data.room.entity.EventEntity
import com.example.homeworktbc.domain.modele.Event
import com.example.homeworktbc.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventApiService: EventApiService,
    private val eventDao: EventDao
) : EventRepository {

    override suspend fun getEvents(): Flow<Resource<List<Event>>> = flow {

        emit(Resource.Loading())

        val response = handleHttpRequest(
           apiCall =  { eventApiService.getEvents() },
            mapToDomain = { it.map { event ->
                event.toDomain()
            } }
        )
        emit(response)
    }
    override suspend fun insertEvent(event: Event) {
        eventDao.insertEvent(
            EventEntity(
                id = event.id,
                name = event.name,
                image = event.image,
                organizer = event.organizer,
                date = event.date,
                info = event.info,
                price = event.price
            )
        )
    }
}