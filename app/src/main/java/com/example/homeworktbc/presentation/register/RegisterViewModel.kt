package com.example.homeworktbc.presentation.register

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.resource.StringResource
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


    private fun validateInputsAndSignUp(email: String, password: String, repeatedPassword: String,context : Context) {
        if (validateInputs(email, password, repeatedPassword,context)) {
            signUpUser(email, password,context)
        }
    }

    private fun validateInputs(
        email: String,
        password: String,
        repeatedPassword: String,
        context: Context
    ): Boolean {
        if (email.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError(context.getString(StringResource.AllFieldsRequired.errorMessageResId)))
            }
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError(context.getString(StringResource.InvalidEmail.errorMessageResId)))
            }
            return false
        }

        if (password.length < 8) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError(context.getString(StringResource.PasswordsDoNotMatch.errorMessageResId)))
            }
            return false
        }

        if (password != repeatedPassword) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError(context.getString(StringResource.PasswordsDoNotMatch.errorMessageResId)))
            }
            return false
        }

        return true
    }

    private fun signUpUser(email: String, password: String,context: Context) {
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
                                result.message ?: context.getString(StringResource.RegistrationFailed.errorMessageResId)
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
                validateInputsAndSignUp(event.email, event.password, event.repeatedPassword, event.context )
            }
        }
    }


}