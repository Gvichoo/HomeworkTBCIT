package com.example.homeworktbc.presentation.fragmentLogin.state

data class LoginState (
    val isLoading : Boolean = false,
    val isSuccess : Boolean = false,

    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isValidForm: Boolean = false,
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val showPassword: Boolean = false,
    )