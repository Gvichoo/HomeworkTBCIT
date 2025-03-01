package com.example.homeworktbc.presentation.settings.event

sealed class SettingsEvent {
    data class SaveLanguage(val languageCode: String) : SettingsEvent()
    data object LogOut : SettingsEvent()
}