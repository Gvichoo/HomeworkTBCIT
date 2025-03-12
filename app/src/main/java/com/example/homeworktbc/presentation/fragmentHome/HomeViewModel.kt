package com.example.homeworktbc.presentation.fragmentHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.homeworktbc.domain.usecase.home.GetUsersPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUsersPagerUseCase: GetUsersPagerUseCase
) : ViewModel() {
    val users = getUsersPagerUseCase.invoke().
    cachedIn(viewModelScope)
}