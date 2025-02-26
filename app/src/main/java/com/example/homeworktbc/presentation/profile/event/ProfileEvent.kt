package com.example.homeworktbc.presentation.profile.event

sealed class ProfileEvent {
    data object Logout : ProfileEvent()
    data object Settings : ProfileEvent()
}