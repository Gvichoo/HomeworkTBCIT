package com.example.homeworktbc.presentation.splash.event

sealed class SplashEvent {
    data object CheckSession : SplashEvent()
}