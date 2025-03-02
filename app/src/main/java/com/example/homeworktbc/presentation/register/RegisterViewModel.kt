package com.example.homeworktbc.presentation.register

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.repository.RegisterRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.register.effect.RegisterEffect
import com.example.homeworktbc.presentation.register.event.RegisterEvent
import com.example.homeworktbc.presentation.register.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : BaseViewModel<RegisterState, RegisterEvent, RegisterEffect>(RegisterState()) {


    private fun validateInputsAndSignUp(email: String, password: String, repeatedPassword: String) {
        if (validateInputs(email, password, repeatedPassword)) {
            signUpUser(email, password)
        }
    }

    private fun validateInputs(email: String, password: String, repeatedPassword: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError("All fields are required!"))
            }
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError("Please enter a valid email address!"))
            }
            return false
        }

        if (password.length < 8) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError("Password must be at least 8 characters!"))
            }
            return false
        }

        if (password != repeatedPassword) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError("Passwords do not match!"))
            }
            return false
        }

        return true
    }

    private fun signUpUser(email: String, password: String) {
        updateState { copy(isLoading = true) }

        viewModelScope.launch {
            registerRepository.register(email, password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        updateState { copy(isSuccess = true) }
                        emitEffect(RegisterEffect.NavToLogInFragment)
                    }

                    is Resource.Failed -> {
                        emitEffect(
                            RegisterEffect.ShowError(
                                result.message ?: "Registration failed"
                            )
                        )
                    }

                    is Resource.Loading -> {
                        updateState { copy(isLoading = true) }
                    }
                }
            }
        }
    }


    override fun obtainEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.LogInClicked -> viewModelScope.launch {
                emitEffect(RegisterEffect.NavToLogInFragment)
            }

            is RegisterEvent.SignUpButtonClicked -> {
                validateInputsAndSignUp(event.email, event.password, event.repeatedPassword)
            }
        }
    }


}