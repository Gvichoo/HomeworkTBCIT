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

        // Fetch cached data from Room
        val cachedEvents = eventDao.getAllEvents().first()
        Log.d("EventRepository", "Fetched events from Room: $cachedEvents")

        if (cachedEvents.isNotEmpty()) {
            // If Room data exists, emit it to avoid unnecessary API calls
            emit(Resource.Success(cachedEvents.map { it.toDomain() }))
        }

        // Fetch data from the API
        val response = handleHttpRequest(
            apiCall = { eventApiService.getEvents() },
            mapToDomain = { it.map { event -> event.toDomain() } }
        )

        if (response is Resource.Success) {
            val newEvents = response.data

            if (newEvents != null) {
                // Compare cached events with new events from the API
                val cachedEventIds = cachedEvents.map { it.id }
                val newEventIds = newEvents.map { it.id }

                // Insert only new or modified data into Room
                val eventsToInsert = newEvents.filter { newEvent -> newEvent.id !in cachedEventIds }
                eventDao.insertEvent(eventsToInsert.map { it.toEntity() })

                // Remove events that are no longer in the API response
                val eventsToDelete = cachedEventIds.filter { it !in newEventIds }
                eventDao.deleteEventsByIds(eventsToDelete)  // Deleting events no longer in API

                Log.d("EventRepository", "Inserted new events into Room.")
            }
        }

        // Emit the data from the API (or from Room if no API response)
        emit(response)
    }
}
