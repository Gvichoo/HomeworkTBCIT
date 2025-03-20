package com.example.challenge.presentation.screen.log_in.effect


sealed class LoginEffect {
    data object NavigateToConnections : LoginEffect()
}