package com.example.homeworktbc.presentation.register.event

import android.content.Context

sealed class RegisterEvent {
    data class SignUpButtonClicked(val email: String, val password: String, val repeatedPassword: String,val context : Context) : RegisterEvent()
    data object LogInClicked : RegisterEvent()
}