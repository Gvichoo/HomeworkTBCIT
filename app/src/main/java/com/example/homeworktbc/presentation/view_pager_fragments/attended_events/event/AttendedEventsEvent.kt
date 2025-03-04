package com.example.homeworktbc.presentation.view_pager_fragments.attended_events.event

sealed class AttendedEventsEvent {
    data class DeleteEvent(val eventId: Int) : AttendedEventsEvent()
}