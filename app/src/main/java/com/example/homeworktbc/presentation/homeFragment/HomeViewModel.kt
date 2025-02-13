package com.example.homeworktbc.presentation.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.homeworktbc.data.paging.repo.Repository

class HomeViewModel(userRepository: Repository) : ViewModel() {
    val users = userRepository.getUsersPager().
    cachedIn(viewModelScope)
}
