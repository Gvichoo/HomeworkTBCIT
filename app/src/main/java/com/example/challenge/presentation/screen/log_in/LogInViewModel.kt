package com.example.challenge.presentation.screen.log_in

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.challenge.domain.core.Resource
import com.example.challenge.domain.usecase.datastore.SaveTokenUseCase
import com.example.challenge.domain.usecase.log_in.LogInUseCase
import com.example.challenge.domain.usecase.validator.EmailValidatorUseCase
import com.example.challenge.domain.usecase.validator.PasswordValidatorUseCase
import com.example.challenge.presentation.base.BaseViewModel
import com.example.challenge.presentation.screen.log_in.effect.LoginEffect
import com.example.challenge.presentation.screen.log_in.event.LogInEvent
import com.example.challenge.presentation.screen.log_in.state.LogInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val emailValidator: EmailValidatorUseCase,
    private val passwordValidator: PasswordValidatorUseCase
) : BaseViewModel<LogInState,LogInEvent,LoginEffect>(LogInState()) {

    fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.LogIn -> validateForm(email = event.email, password = event.password)
            is LogInEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }


    private fun logIn(email: String,password: String){
        viewModelScope.launch {
            logInUseCase(email = email, password = password).collect { that ->
                when(that){
                    is Resource.Failed -> {
                        Log.e("LogInViewModel", "LogIn failed: ${that.message}")
                        updateState { copy(errorMessage = errorMessage) }
                    }
                    is Resource.Loading ->{
                        updateState { copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        val token = that.data?.firstOrNull()?.accessToken
                        Log.d("LogInViewModel", "LogIn success: $token")
                        updateState { copy(accessToken = token) }
                        token?.let { saveTokenUseCase(it) }
                        emitEffect(LoginEffect.NavigateToConnections)
                    }

                }
            }
        }
    }


    private fun validateForm(email: String, password: String) {
        val isEmailValid = emailValidator(email)
        val isPasswordValid = passwordValidator(password)

        val areFieldsValid =
            listOf(isEmailValid, isPasswordValid)
                .all { it }

        if (!areFieldsValid) {
            updateErrorMessage(message = "Fields are not valid!")
            return
        }

        Log.d("LogInViewModel", "Form is valid, calling logIn()")
        logIn(email = email, password = password)
    }


    private fun updateErrorMessage(message: String?) {
        updateState { copy(errorMessage = message) }
    }

    override fun obtainEvent(event: LogInEvent) {
        when(event){
            is LogInEvent.LogIn -> validateForm( event.email, event.password)
            LogInEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }
}
