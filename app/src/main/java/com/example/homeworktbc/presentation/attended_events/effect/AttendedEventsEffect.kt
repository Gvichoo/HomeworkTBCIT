package com.example.homeworktbc.presentation.attended_events.effect

sealed class AttendedEventsEffect {
    data class ShowMessage(val message: String) : AttendedEventsEffect()
}