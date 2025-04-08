package com.example.homeworktbc.presentation.screen.register.effect

sealed interface RegisterEffect {
    data class ShowError(val message : String) : RegisterEffect
    data object NavToLogInFragment : RegisterEffect
}