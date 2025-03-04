package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.mapper.toDomain
import com.example.homeworktbc.data.mapper.toEntity
import com.example.homeworktbc.data.room.dao.AttendedEventDao
import com.example.homeworktbc.domain.modele.AttendedEvent
import com.example.homeworktbc.domain.repository.AttendedEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AttendedEventRepositoryImpl @Inject constructor(
    private val attendedEventDao: AttendedEventDao
) : AttendedEventRepository {
    override suspend fun insertAttendedEvent(attendedEvent: AttendedEvent) {
        attendedEventDao.insertEvent(attendedEvent.toEntity())
    }

    override fun getAttendedEvents(): Flow<List<AttendedEvent>> {
        return attendedEventDao.getAllEvents().map { it.map { event ->
            event.toDomain() } }
    }

    override suspend fun deleteEvent(eventIds: Int) {
        attendedEventDao.deleteEventsByIds(eventIds)
    }

}