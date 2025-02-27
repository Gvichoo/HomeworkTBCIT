package com.example.homeworktbc.presentation.settings.event

sealed class SettingsEvent {
    data class LanguageSelected(val language: String) : SettingsEvent()
    data object LoadSavedLanguage : SettingsEvent()
}