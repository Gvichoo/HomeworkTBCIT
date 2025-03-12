package com.example.homeworktbc.presentation.fragmentProfile.event

sealed class ProfileEvent {
    data object LogoutButtonClicked : ProfileEvent()
}
