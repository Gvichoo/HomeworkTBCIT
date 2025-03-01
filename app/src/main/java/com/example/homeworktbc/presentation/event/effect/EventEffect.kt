package com.example.homeworktbc.presentation.event.effect

sealed class EventEffect {
    data object ShowErrorMessage : EventEffect()
    data object ShowSuccessMessage : EventEffect()
    data object NavToAddEventsFragment : EventEffect()
}