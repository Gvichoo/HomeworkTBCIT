package com.example.homeworktbc.presentation.fragment

import android.health.connect.datatypes.ExerciseRoute
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.homeworktbc.data.remote.MyApi
import com.example.homeworktbc.data.remote.PageData
import com.example.homeworktbc.data.resource.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: MyApi) : ViewModel() {

    private val _response = MutableStateFlow<Results<List<PageData>>>(Results.Loading)
    val response: StateFlow<Results<List<PageData>>> = _response

    private var isDataFetched = false

    fun isDataLoaded(): Boolean {
        return isDataFetched
    }

    fun fetchData() {

        if (isDataFetched) return

        viewModelScope.launch {
            _response.value = Results.Loading
            try {
                val response = apiService.fetchPage()
                if (response.isSuccessful && response.body() != null) {
                    _response.value = Results.Success(response.body()!!)
                } else {
                    _response.value = Results.Failed(Exception("Error: ${response.code()}"))
                }
            } catch (e: Exception) {
                _response.value = Results.Failed(e)
            }
        }
    }
}