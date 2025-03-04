package com.example.homeworktbc.presentation.view_pager_fragments.attended_events.effect

sealed class AttendedEventsEffect {
    data class ShowMessage(val message: String) : AttendedEventsEffect()
}