package com.example.homeworktbc.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.domain.modele.AttendedEvent
import com.example.homeworktbc.domain.repository.AttendedEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val attendedEventRepository: AttendedEventRepository
) : ViewModel() {

    fun insertAttendedEvent(attendedEvent: AttendedEvent){
        viewModelScope.launch {
            attendedEventRepository.insertAttendedEvent(attendedEvent)
        }
    }

}