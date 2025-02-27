package com.example.homeworktbc.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.di.repository.DataStoreRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.splash.effect.SplashEffect
import com.example.homeworktbc.presentation.splash.event.SplashEvent
import com.example.homeworktbc.presentation.splash.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<SplashState, SplashEvent, SplashEffect>(SplashState()) {


    private fun checkSession() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            val email = dataStoreRepository.readValue(PreferenceKeys.email).firstOrNull()
            val isLoggedIn = !email.isNullOrEmpty()
            updateState { copy(isLoading = false, isLoggedIn = isLoggedIn) }
            emitEffect(
                if (isLoggedIn) SplashEffect.NavigateToEvents else SplashEffect.NavigateToLogin
            )
        }
    }

    override fun obtainEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.CheckSession -> checkSession()
        }
    }
}