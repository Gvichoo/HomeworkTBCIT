package com.example.homeworktbc.presentation.event.effect

sealed class EventEffect {
    object ShowErrorMessage : EventEffect()
    object ShowSuccessMessage : EventEffect()
    object NavToAddEventsFragment : EventEffect()
}