package com.example.homeworktbc.presentation.login.event

sealed class LoginEvent {
    data class LoginButtonClicked(val email: String, val password: String) : LoginEvent()
    data object SignUpClicked : LoginEvent()

}