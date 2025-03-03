package com.example.homeworktbc.presentation.add_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.room.dao.EventDao
import com.example.homeworktbc.data.room.entity.EventEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventDao: EventDao
) : ViewModel() {

    fun insertEvent(event: EventEntity) {
        viewModelScope.launch {
            eventDao.insertEvent(event)
        }
    }

}