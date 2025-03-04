package com.example.homeworktbc.presentation.event

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.repository.EventRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.event.effect.EventEffect
import com.example.homeworktbc.presentation.event.event.EventEvent
import com.example.homeworktbc.presentation.event.state.EventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : BaseViewModel<EventState,EventEvent,EventEffect>(EventState()) {



//    fun saveAttendedEvent(event: Event) {
//        viewModelScope.launch {
//            eventRepository.insertEvent(event)
//            emitEffect(EventEffect.ShowSuccessMessage)
//        }
//    }
//
//    fun getAttendedEvents(): Flow<List<Event>> {
//        return eventRepository.getAllAttendedEvents()
//    }




    private var isDataFetched = false

    private fun isDataLoaded(): Boolean {
        return isDataFetched
    }

    fun getEvents() {

        if (isDataLoaded()) return

        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            eventRepository.getEvents().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        updateState { copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        updateState { copy(isLoading = false, events = resource.data) }
                        isDataFetched = true
                        Log.d("EventViewModel", "Events fetched: ${resource.data}")

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
        when(event) {
            EventEvent.FetchEvents -> {
                getEvents()
            }

            EventEvent.AddEventClicked -> viewModelScope.launch {
                emitEffect(EventEffect.NavToAddEventsFragment)
            }

            is EventEvent.NewEventAdded -> {
            }
        }
    }
}



