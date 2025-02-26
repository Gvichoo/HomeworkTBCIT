package com.example.homeworktbc.presentation.login.event

sealed class LoginEvent {
    data class LoginButtonClicked(val email: String, val password: String,val rememberMe : Boolean) : LoginEvent()
    data object SignUpClicked : LoginEvent()


}