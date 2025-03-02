package com.example.homeworktbc.presentation.event.state

import com.example.homeworktbc.domain.modele.Event

data class EventState(
    val isLoading: Boolean = false,
    val events: List<Event>? = null,
    val errorMessage: String? = null,
    val isEventAdded: Boolean = false
)