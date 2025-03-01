package com.example.homeworktbc.presentation.settings.effect


sealed class SettingsEffect {
    data object NavigateToLogin : SettingsEffect()
    data class ShowLanguageChangeMessage(val message: String) : SettingsEffect()
}