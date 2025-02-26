package com.example.homeworktbc.presentation.settings.event

sealed class SettingsEvent {
    data object ChangeLanguage : SettingsEvent()
}