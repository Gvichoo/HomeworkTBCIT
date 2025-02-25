package com.example.homeworktbc.presentation.register.effect

sealed class RegisterEffect {
    data class ShowError(val message: String) : RegisterEffect()
    data object NavToLogInFragment : RegisterEffect()
}
