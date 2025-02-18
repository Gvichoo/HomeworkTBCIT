package com.example.homeworktbc.domain

import com.example.homeworktbc.presentation.mainFragment.UserViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface UserViewModelFactory {
    fun create(userId: String): UserViewModel
}