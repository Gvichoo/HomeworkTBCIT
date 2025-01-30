package com.example.homeworktbc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val protoRepository: ProtoRepository) : ViewModel() {

    private val _savedUserData = MutableStateFlow<MessageUser?>(null)

    private val _displayedUserData = MutableStateFlow<MessageUser?>(null)
    val displayedUserData: StateFlow<MessageUser?> = _displayedUserData.asStateFlow()

    init {
        viewModelScope.launch {
            protoRepository.readProto.collect { user ->
                _savedUserData.value = user
            }
        }
    }

    fun updateValue(firstName: String, lastName: String, email: String) {
        viewModelScope.launch {
            protoRepository.updateValue(firstName, lastName, email)
        }
    }

    fun readData() {
        _displayedUserData.value = _savedUserData.value
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val protoRepository = ProtoRepository.getInstance(application)
                MainViewModel(protoRepository)
            }
        }
    }
}