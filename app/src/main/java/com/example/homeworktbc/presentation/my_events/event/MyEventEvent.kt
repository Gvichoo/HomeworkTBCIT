package com.example.homeworktbc.presentation.my_events.event

import com.example.homeworktbc.data.remote.model.EventDto

sealed class MyEventEvent {
    data class NewEventAdded(val event: EventDto) : MyEventEvent()
}