package com.example.homeworktbc.presentation.attended_events.state

import com.example.homeworktbc.domain.modele.AttendedEvent

data class AttendedEventsState(
    val events: List<AttendedEvent>? = null
)
