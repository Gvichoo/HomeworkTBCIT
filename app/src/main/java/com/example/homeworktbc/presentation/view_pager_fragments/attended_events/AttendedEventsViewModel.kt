package com.example.homeworktbc.presentation.view_pager_fragments.attended_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.domain.modele.AttendedEvent
import com.example.homeworktbc.domain.repository.AttendedEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendedEventsViewModel @Inject constructor(
    private val attendedEventRepository: AttendedEventRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<AttendedEvent>?>(null)
    val uiState: StateFlow<List<AttendedEvent>?> = _uiState


    init {
        getAttendedEvent()
    }

    fun getAttendedEvent(){
        viewModelScope.launch {
            attendedEventRepository.getAttendedEvents().collectLatest {
                _uiState.value = it
            }
        }
    }

    fun deleteEvents(eventIds : Int){
        viewModelScope.launch {
            attendedEventRepository.deleteEvent(eventIds)
        }
    }
}