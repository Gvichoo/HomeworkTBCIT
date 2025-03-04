package com.example.homeworktbc.presentation.event.event


sealed class EventEvent {
    object FetchEvents : EventEvent()
    object AddEventClicked : EventEvent()
}