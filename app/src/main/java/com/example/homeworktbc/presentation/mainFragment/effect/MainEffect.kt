package com.example.homeworktbc.presentation.mainFragment.effect

sealed class MainEffect {
    data class ShowMessage(val message: String) : MainEffect()
}