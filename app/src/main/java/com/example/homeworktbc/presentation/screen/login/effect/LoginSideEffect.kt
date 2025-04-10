package com.example.homeworktbc.presentation.screen.login.effect

sealed interface LoginSideEffect {
    data object NavigateToRegister : LoginSideEffect
    data object NavigateToHome : LoginSideEffect
    data class ShowAuthError(val message: String?) : LoginSideEffect
}