package com.example.homeworktbc.presentation.mainFragment

import androidx.lifecycle.ViewModel
import com.example.homeworktbc.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private var userRepository: UserRepository
) : ViewModel() {

    fun getUserName(): String {
        return userRepository.getUser()
    }
}