package com.example.homeworktbc.presentation.fragment

import android.health.connect.datatypes.ExerciseRoute
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val serverRepository:
) : ViewModel() {

    private val _serverResponse = MutableStateFlow<Resource<List<ExerciseRoute.Location>>>(Resource.Loading)
    val serverResponse: StateFlow<Resource<List<ExerciseRoute.Location>>> = _serverResponse

    init {
        fetchLocationData()
    }

    private fun fetchLocationData() {
        serverRepository.getUsers().launchDataLoad(viewModelScope, _serverResponse)
    }
}
