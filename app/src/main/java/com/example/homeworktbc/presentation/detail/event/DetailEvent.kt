package com.example.homeworktbc.presentation.detail.event

import com.example.homeworktbc.domain.modele.AttendedEvent

sealed class DetailEvent {
    data class InsertAttendedEvent(val attendedEvent: AttendedEvent) : DetailEvent()
}