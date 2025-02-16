package com.example.homeworktbc.presentation.fragmentProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.domain.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val savedEmail = dataStoreRepository.readValue(PreferenceKeys.email)
        .stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun logout() = viewModelScope.launch {
        dataStoreRepository.removeByKey(PreferenceKeys.email)
    }
}