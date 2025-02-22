package com.example.homeworktbc.presentation.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.model.Event
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _events =
        MutableStateFlow<Resource<List<Event>>>(Resource.Loading())
    val events: StateFlow<Resource<List<Event>>> = _events

    fun getEvents() {
        viewModelScope.launch {
            eventRepository.getEvents().collect { resource ->
                _events.value = resource
            }
        }
    }
}



