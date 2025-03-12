package com.example.homeworktbc.presentation.fragmentLogin

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.local.datastore.PreferenceKeys
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.usecase.dataStore.SaveValueUseCase
import com.example.homeworktbc.domain.usecase.login.LoginUseCase
import com.example.homeworktbc.presentation.baseViewModel.BaseViewModel
import com.example.homeworktbc.presentation.fragmentLogin.effect.LoginEffect
import com.example.homeworktbc.presentation.fragmentLogin.event.LoginEvent
import com.example.homeworktbc.presentation.fragmentLogin.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepositoryUseCase: LoginUseCase,
    private val saveValueUseCase: SaveValueUseCase
) : BaseViewModel<LoginState, LoginEvent, LoginEffect>(LoginState()) {


    private fun validateInputsAndLogin(email: String, password: String, rememberMe: Boolean) {
        if (validateInputs(email, password, rememberMe)) {
            loginUser(email, password, rememberMe)
        }
    }


    private fun validateInputs(email: String, password: String, rememberMe: Boolean): Boolean {
        if (email.isEmpty() && password.isEmpty()) {
            viewModelScope.launch {
                emitEffect(LoginEffect.ShowError("Inputs are empty!"))
            }
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewModelScope.launch {
                emitEffect(LoginEffect.ShowError("Email doesn't match!"))
            }
            return false
        }

        if (password.length < 8) {
            viewModelScope.launch {
                emitEffect(LoginEffect.ShowError("Password Should be at least 8 characters!"))
            }
            return false
        }
        if (rememberMe) {
            saveEmailToDataStore(email)
        }

        return true
    }


    private fun loginUser(email: String, password: String, rememberMe: Boolean) {
        updateState { copy(isLoading = true) }

        viewModelScope.launch {
            when (val result = loginRepositoryUseCase.invoke(email, password, rememberMe)) {
                is Resource.Failed -> {
                    emitEffect(
                        LoginEffect.ShowError(
                            result.message ?: "Failed login!"
                        )
                    )
                    updateState { copy(isLoading = false) }
                }
                is Resource.Loading -> {
                    updateState { copy(isLoading = true) }
                }

                is Resource.Success -> {
                    updateState { copy(isSuccess = true) }
                    emitEffect(LoginEffect.NavToHomeFragment)
                    if (rememberMe) {
                        saveEmailToDataStore(email)
                    }
                    updateState { copy(isLoading = false) }
                }
            }
        }
    }


    private fun saveEmailToDataStore(email: String) {
        viewModelScope.launch {
            val emailKey = PreferenceKeys.email
            saveValueUseCase(emailKey, email)
        }
    }

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginButtonClicked -> validateInputsAndLogin(event.email, event.password, event.rememberMe
            )
            LoginEvent.RegisterButtonClicked -> viewModelScope.launch {
                emitEffect(LoginEffect.NavToRegisterFragment)
            }
        }
    }

}
