package com.example.homeworktbc.presentation.profile.effect

sealed class ProfileEffect {
    data object NavigateToLogin : ProfileEffect()
    data object NavigateToSetting : ProfileEffect()
}