package com.example.homeworktbc.presentation.splash.effect

sealed class SplashEffect {
    data object NavigateToHome : SplashEffect()
    data object NavigateToLogin : SplashEffect()
}
