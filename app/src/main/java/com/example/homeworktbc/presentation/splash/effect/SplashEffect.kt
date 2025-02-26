package com.example.homeworktbc.presentation.splash.effect

sealed class SplashEffect {
    data object NavigateToEvents : SplashEffect()
    data object NavigateToLogin : SplashEffect()
}