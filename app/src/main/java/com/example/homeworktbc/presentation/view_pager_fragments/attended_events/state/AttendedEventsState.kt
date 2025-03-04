package com.example.homeworktbc.presentation.view_pager_fragments.attended_events.state

import com.example.homeworktbc.domain.modele.AttendedEvent

data class AttendedEventsState(
    val events: List<AttendedEvent>? = null
)
