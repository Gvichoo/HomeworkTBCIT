package com.example.homeworktbc.paging.presentation.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.homeworktbc.paging.Repository

class HomeViewModel(userRepository: Repository) : ViewModel() {
    val users = userRepository.getUsersPager().
    cachedIn(viewModelScope)
}
