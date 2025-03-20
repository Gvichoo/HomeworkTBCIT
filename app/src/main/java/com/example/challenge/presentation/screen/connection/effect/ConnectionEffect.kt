package com.example.challenge.presentation.screen.connection.effect

sealed class ConnectionEffect {
    data object NavigateToLogIn : ConnectionEffect()
}