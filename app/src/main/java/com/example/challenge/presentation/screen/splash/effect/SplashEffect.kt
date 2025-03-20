package com.example.challenge.presentation.screen.splash.effect


sealed class SplashEffect {
    data object NavigateToLogIn : SplashEffect()
    data object NavigateToConnections : SplashEffect()
}