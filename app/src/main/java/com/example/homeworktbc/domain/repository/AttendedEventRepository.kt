package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.domain.modele.AttendedEvent
import kotlinx.coroutines.flow.Flow

interface AttendedEventRepository {

    suspend fun insertAttendedEvent(attendedEvent: AttendedEvent)

    fun getAttendedEvents():Flow<List<AttendedEvent>>

    suspend fun deleteEvent(eventIds : Int)

}