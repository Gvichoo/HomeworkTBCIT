package com.example.homeworktbc.presentation.mainFragment.state

import com.example.homeworktbc.presentation.model.CategoryPresentation

data class MainState(
    val isLoading: Boolean = false,
    val isSuccess : Boolean = false,
    val categories: List<CategoryPresentation> = emptyList(),
    val errorMessage : String? = null
)