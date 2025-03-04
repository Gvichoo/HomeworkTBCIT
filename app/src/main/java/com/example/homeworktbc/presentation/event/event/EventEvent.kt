package com.example.homeworktbc.presentation.event.event

import com.example.homeworktbc.domain.modele.Event

sealed class EventEvent {
    object FetchEvents : EventEvent()
    object AddEventClicked : EventEvent()
    data class NewEventAdded(val event: Event) : EventEvent()
}