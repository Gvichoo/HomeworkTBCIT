package com.example.homeworktbc.presentation.my_events

import com.example.homeworktbc.data.remote.model.EventDto
import com.example.homeworktbc.domain.modele.Event
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.my_events.effect.MyEventEffect
import com.example.homeworktbc.presentation.my_events.event.MyEventEvent
import com.example.homeworktbc.presentation.my_events.state.MyEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor() : BaseViewModel<MyEventState, MyEventEvent, MyEventEffect>(
    MyEventState()
) {
    override fun obtainEvent(event: MyEventEvent) {

    }
    fun addNewEvent(eventDto: EventDto) {
        updateState {
            copy(events = listOf(eventDto) + events)
        }
    }


}


