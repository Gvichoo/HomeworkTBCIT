package com.example.homeworktbc.presentation.attended_events.event

sealed class AttendedEventsEvent {
    data class DeleteEvent(val eventId: Int) : AttendedEventsEvent()
}