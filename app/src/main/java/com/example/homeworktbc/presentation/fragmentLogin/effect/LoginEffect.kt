package com.example.homeworktbc.presentation.fragmentLogin.effect

sealed interface LoginEffect {
    data object NavToHomeFragment : LoginEffect
    data object NavToRegisterFragment : LoginEffect
    data object ShowError : LoginEffect
}