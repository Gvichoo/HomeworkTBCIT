package com.example.homeworktbc.presentation.view_pager_fragments.my_events

import android.util.Log
import com.example.homeworktbc.data.model.EventDto
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.view_pager_fragments.my_events.effect.MyEventEffect
import com.example.homeworktbc.presentation.view_pager_fragments.my_events.event.MyEventEvent
import com.example.homeworktbc.presentation.view_pager_fragments.my_events.state.MyEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
) : BaseViewModel<MyEventState, MyEventEvent, MyEventEffect>(MyEventState()) {

    override fun obtainEvent(event: MyEventEvent) {
        when(event){
            is MyEventEvent.NewEventAdded -> {
                updateState {
                    Log.d("MyEventsViewModel", "Adding new event: ${event.event.name}")
                    copy(events = listOf(event.event) + (events ?: emptyList()))
                }
            }
        }
    }

    fun addNewEvent(event: EventDto) {
        Log.d("MyEventsViewModel", "Adding new event to the list: $event")
        updateState {
            Log.d("MyEventsViewModel", "Current events: $events")
            val updatedEvents = (events ?: emptyList()) + event
            Log.d("MyEventsViewModel", "Updated events list: $updatedEvents")
            copy(events = updatedEvents)
        }
    }

}

