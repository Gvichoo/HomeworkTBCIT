package com.example.homeworktbc.presentation.fragmentRegister.effect

sealed interface RegisterEffect {
    data class ShowError(val message : String) : RegisterEffect
    data object NavToLogInFragment : RegisterEffect
}