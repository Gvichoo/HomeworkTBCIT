package com.example.homeworktbc.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.domain.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    fun checkSession(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val email = dataStoreRepository.readValue(PreferenceKeys.email).firstOrNull()
            onResult(!email.isNullOrEmpty())
        }
    }
}