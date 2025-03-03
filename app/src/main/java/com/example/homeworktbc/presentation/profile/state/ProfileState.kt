package com.example.homeworktbc.presentation.profile.state

data class ProfileState(
    val isLoggingOut: Boolean = false,
    val email: String = "",
    val displayName: String = ""
)