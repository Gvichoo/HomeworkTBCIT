package com.example.homeworktbc.presentation.splash

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.local.datastore.PreferenceKeys
import com.example.homeworktbc.domain.usecase.dataStore.ReadValueUseCase
import com.example.homeworktbc.presentation.baseViewModel.BaseViewModel
import com.example.homeworktbc.presentation.splash.effect.SplashEffect
import com.example.homeworktbc.presentation.splash.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readValueUseCase: ReadValueUseCase
) : BaseViewModel<SplashState, Unit, SplashEffect>(SplashState()) {

    fun checkSession() {
        viewModelScope.launch {
            val email = readValueUseCase.invoke(PreferenceKeys.email).firstOrNull()
            val isLoggedIn = !email.isNullOrEmpty()
            if (isLoggedIn) {
                emitEffect(SplashEffect.NavigateToHome)
            } else {
                emitEffect(SplashEffect.NavigateToLogin)
            }
        }
    }

    override fun obtainEvent(event: Unit) {
    }
}

