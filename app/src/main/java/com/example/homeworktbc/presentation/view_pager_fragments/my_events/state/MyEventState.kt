package com.example.homeworktbc.presentation.view_pager_fragments.my_events.state

import com.example.homeworktbc.data.model.EventDto

data class MyEventState (
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEventAdded: Boolean = false,
    val events: List<EventDto>? = emptyList()
)