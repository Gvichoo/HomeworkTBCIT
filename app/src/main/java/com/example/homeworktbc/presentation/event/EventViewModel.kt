package com.example.homeworktbc.presentation.event

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.model.Event
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.EventRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.event.effect.EventEffect
import com.example.homeworktbc.presentation.event.event.EventEvent
import com.example.homeworktbc.presentation.event.state.EventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : BaseViewModel<EventState,EventEvent,EventEffect>(EventState()) {


    fun getEvents() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            eventRepository.getEvents().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        updateState { copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        updateState { copy(isLoading = false, events = resource.data) }
                    }
                    is Resource.Failed -> {
                        updateState { copy(isLoading = false, errorMessage = resource.message) }
                        emitEffect(EventEffect.ShowErrorMessage)
                    }
                }
            }
        }
    }

    override fun obtainEvent(event: EventEvent) {
        when(event){
            EventEvent.FetchEvents -> {
                getEvents()
            }
        }
    }
}



