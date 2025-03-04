package com.example.homeworktbc.presentation.event.state

import com.example.homeworktbc.domain.modele.Event

data class EventState(
    val isLoading: Boolean = false,
    val events: List<Event>? = emptyList(),
    val errorMessage: String? = null,
)