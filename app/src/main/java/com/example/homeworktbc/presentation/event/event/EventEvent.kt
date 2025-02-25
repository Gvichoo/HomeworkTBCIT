package com.example.homeworktbc.presentation.event.event

sealed class EventEvent {
    data object FetchEvents : EventEvent()
}