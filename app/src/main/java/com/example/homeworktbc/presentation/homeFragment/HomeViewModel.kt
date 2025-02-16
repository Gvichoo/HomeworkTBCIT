package com.example.homeworktbc.presentation.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.homeworktbc.data.repository.HomeRepositoryImpl
import com.example.homeworktbc.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRepository: HomeRepository
) : ViewModel() {
    val users = homeRepository.getUsersPager().
    cachedIn(viewModelScope)
}
