package com.example.homeworktbc.presentation.view_pager_fragments.my_events.event

import com.example.homeworktbc.data.model.EventDto

sealed class MyEventEvent {
    data class NewEventAdded(val event: EventDto) : MyEventEvent()
}