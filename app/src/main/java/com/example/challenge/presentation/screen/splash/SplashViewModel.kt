package com.example.challenge.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.challenge.domain.usecase.datastore.GetTokenUseCase
import com.example.challenge.presentation.base.BaseViewModel
import com.example.challenge.presentation.screen.splash.effect.SplashEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getTokenUseCase: GetTokenUseCase) :
    BaseViewModel<Unit,Unit,SplashEffect>(Unit) {

    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            getTokenUseCase().collect {
                if (it.isEmpty())
                    emitEffect(SplashEffect.NavigateToLogIn)
                else
                    emitEffect(SplashEffect.NavigateToConnections)
            }
        }
    }


    override fun obtainEvent(event: Unit) {
        TODO("Not yet implemented")
    }
}