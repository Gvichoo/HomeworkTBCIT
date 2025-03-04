package com.example.homeworktbc.presentation.view_pager_fragments.attended_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.domain.repository.AttendedEventRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.effect.AttendedEventsEffect
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.event.AttendedEventsEvent
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.state.AttendedEventsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendedEventsViewModel @Inject constructor(
    private val attendedEventRepository: AttendedEventRepository
) : BaseViewModel<AttendedEventsState, AttendedEventsEvent, AttendedEventsEffect>(AttendedEventsState()) {

    init {
        getAttendedEvent()
    }

    override fun obtainEvent(event: AttendedEventsEvent) {
        when (event) {
            is AttendedEventsEvent.DeleteEvent -> deleteEvents(event.eventId)
        }
    }

    private fun getAttendedEvent() {
        viewModelScope.launch {
            attendedEventRepository.getAttendedEvents().collectLatest {
                updateState {copy(events = it) }
            }
        }
    }

    private fun deleteEvents(eventId: Int) {
        viewModelScope.launch {
            attendedEventRepository.deleteEvent(eventId)
            getAttendedEvent()
        }
    }
}
