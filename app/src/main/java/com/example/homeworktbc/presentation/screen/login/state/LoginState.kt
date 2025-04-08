package com.example.homeworktbc.presentation.screen.login.state

data class LoginState (
    val isLoading : Boolean = false,
    val isSuccess : Boolean = false,
    val error: String? = null
)
