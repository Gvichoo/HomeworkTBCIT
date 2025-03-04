package com.example.homeworktbc.presentation.add_event

import com.example.homeworktbc.presentation.add_event.effect.AddEventEffect
import com.example.homeworktbc.presentation.add_event.event.AddEventEvent
import com.example.homeworktbc.presentation.add_event.state.AddEventState
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor() : BaseViewModel<AddEventState,AddEventEvent,AddEventEffect>(AddEventState()) {
    override fun obtainEvent(event: AddEventEvent) {

    }

}