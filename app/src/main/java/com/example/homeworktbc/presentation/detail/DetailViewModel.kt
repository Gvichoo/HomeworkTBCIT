package com.example.homeworktbc.presentation.detail

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.domain.modele.AttendedEvent
import com.example.homeworktbc.domain.repository.AttendedEventRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.detail.effect.DetailEffect
import com.example.homeworktbc.presentation.detail.event.DetailEvent
import com.example.homeworktbc.presentation.detail.state.DetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val attendedEventRepository: AttendedEventRepository
) : BaseViewModel<DetailViewState,DetailEvent,DetailEffect>(DetailViewState()) {


    override fun obtainEvent(event: DetailEvent) {
        when(event){
            is DetailEvent.InsertAttendedEvent -> insertAttendedEvent(event.attendedEvent)
        }
    }


    private fun insertAttendedEvent(attendedEvent: AttendedEvent){
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            attendedEventRepository.insertAttendedEvent(attendedEvent)
            updateState { copy(isLoading = false) }
            emitEffect(DetailEffect.EventInserted)
        }
    }
}