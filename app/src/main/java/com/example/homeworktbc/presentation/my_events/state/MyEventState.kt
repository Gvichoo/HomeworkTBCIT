package com.example.homeworktbc.presentation.my_events.state

import com.example.homeworktbc.data.remote.model.EventDto

data class MyEventState(
    val events: List<EventDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)