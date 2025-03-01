package com.example.homeworktbc.presentation.event.event

import com.example.homeworktbc.data.model.Event

sealed class EventEvent {
    data object FetchEvents : EventEvent()
    data object AddEventClicked : EventEvent()
    data class NewEventAdded(val event: Event) : EventEvent()
}