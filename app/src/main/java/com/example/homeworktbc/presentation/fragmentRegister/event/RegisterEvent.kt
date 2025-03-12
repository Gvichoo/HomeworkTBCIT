package com.example.homeworktbc.presentation.fragmentRegister.event

sealed class RegisterEvent {
    data class SignUpButtonClicked(val email: String, val password: String, val repeatedPassword: String) : RegisterEvent()
}