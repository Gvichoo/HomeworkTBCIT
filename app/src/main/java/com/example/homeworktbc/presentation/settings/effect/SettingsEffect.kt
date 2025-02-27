package com.example.homeworktbc.presentation.settings.effect

sealed class SettingsEffect {
    data object RestartApp : SettingsEffect()
}