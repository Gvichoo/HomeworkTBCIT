package com.example.homeworktbc.data.repository

import android.util.Log
import com.example.homeworktbc.data.api.EventApiService
import com.example.homeworktbc.data.mapper.toDomain
import com.example.homeworktbc.data.mapper.toEntity
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.data.room.dao.EventDao
import com.example.homeworktbc.domain.modele.Event
import com.example.homeworktbc.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventApiService: EventApiService,
    private val eventDao: EventDao
) : EventRepository {

    override suspend fun getEvents(): Flow<Resource<List<Event>>> = flow {
        emit(Resource.Loading())

        val cachedEvents = eventDao.getAllEvents().first()

        if (cachedEvents.isNotEmpty()) {
            emit(Resource.Success(cachedEvents.map { it.toDomain() }))
        }

        val response = handleHttpRequest(
            apiCall = { eventApiService.getEvents() },
            mapToDomain = { it.map { event -> event.toDomain() } }
        )

        if (response is Resource.Success) {
            val newEvents = response.data

            if (newEvents != null) {
                val cachedEventIds = cachedEvents.map { it.id }

                val newEventIds = newEvents.map { it.id }

                val eventsToInsert = newEvents.filter { newEvent -> newEvent.id !in cachedEventIds }
                if (eventsToInsert.isNotEmpty()) {
                    eventDao.insertEvent(eventsToInsert.map { it.toEntity() })
                    Log.d("EventRepository", "Inserted new events: ${eventsToInsert.size}")
                }


                val eventsToDelete = cachedEventIds.filter { it !in newEventIds }
                eventDao.deleteEventsByIds(eventsToDelete)

                Log.d("EventRepository", "Inserted new events and deleted outdated ones.")
            }
        }
        emit(response)
    }

    override suspend fun addEvent(event: Event) {

        eventDao.insertEvent( event)
        Log.d("EventRepository", "Event added: $event")
    }

}

