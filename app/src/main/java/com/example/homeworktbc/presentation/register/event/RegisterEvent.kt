package com.example.homeworktbc.presentation.register.event

sealed class RegisterEvent {
    data class SignUpButtonClicked(val email: String, val password: String, val repeatedPassword: String) : RegisterEvent()
    data object LogInClicked : RegisterEvent()
}