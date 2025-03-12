package com.example.homeworktbc.presentation.fragmentRegister.state

data class RegisterState (
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)