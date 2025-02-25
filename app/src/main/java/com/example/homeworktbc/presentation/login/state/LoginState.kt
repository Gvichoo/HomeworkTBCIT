package com.example.homeworktbc.presentation.login.state

data class LoginState (
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)