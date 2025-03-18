package com.example.homeworktbc.presentation.mainFragment.event

sealed class MainEvent {
    data object FetchCategories : MainEvent()
    data class FilterCategories(val query: String) : MainEvent()
}