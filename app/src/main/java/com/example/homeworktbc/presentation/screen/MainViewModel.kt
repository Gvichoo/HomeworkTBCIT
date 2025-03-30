package com.example.homeworktbc.presentation.screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.presentation.base.BaseViewModel
import com.example.homeworktbc.presentation.screen.effect.MainEffect
import com.example.homeworktbc.presentation.screen.event.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<Unit,MainEvent,MainEffect>(Unit) {

    override fun obtainEvent(event: MainEvent) {
        when(event){
            MainEvent.AccountClicked ->viewModelScope.launch {
                Log.d("MainViewModel", "AccountClicked triggered")
                emitEffect(MainEffect.NavToBottomSheet) }
        }
    }
}