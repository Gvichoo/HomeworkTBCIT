package com.example.homeworktbc.presentation.profile.event

sealed class ProfileEvent {
    data object Settings : ProfileEvent()
}