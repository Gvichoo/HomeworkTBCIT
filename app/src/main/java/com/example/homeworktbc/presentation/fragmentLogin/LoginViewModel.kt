package com.example.homeworktbc.presentation.fragmentLogin

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.local.datastore.PreferenceKeys
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.core.ValidationResult
import com.example.homeworktbc.domain.usecase.dataStore.SaveValueUseCase
import com.example.homeworktbc.domain.usecase.login.LoginUseCase
import com.example.homeworktbc.domain.usecase.validation.EmailValidationUseCase
import com.example.homeworktbc.domain.usecase.validation.PasswordValidationUseCase
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
    private val saveValueUseCase: SaveValueUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase
) : BaseViewModel<LoginState, LoginEvent, LoginEffect>(LoginState()) {


    private fun validateInputsAndLogin(email: String, password: String, rememberMe: Boolean) {
        val emailValidation = emailValidationUseCase(email)
        val passwordValidation = passwordValidationUseCase(password)
        when {
            emailValidation is ValidationResult.Failure -> {
                showError(emailValidation.error.message)
                return
            }
            passwordValidation is ValidationResult.Failure -> {
                showError(passwordValidation.error.message)
                return
            }
            else -> {
                loginUser(email, password, rememberMe)
            }
        }
    }



    private fun showError(errorMessage: String) {
        viewModelScope.launch {
            emitEffect(LoginEffect.ShowError(errorMessage))
        }
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
                        saveEmail(email)
                    }
                    updateState { copy(isLoading = false) }
                }
            }
        }
    }


    private fun saveEmail(email: String) {
        viewModelScope.launch {
            val emailKey = PreferenceKeys.EMAIL_KEY
            saveValueUseCase(emailKey, email)
        }
    }


    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginButtonClicked -> viewModelScope.launch {
                validateInputsAndLogin(event.email, event.password, event.rememberMe
                )
            }
            LoginEvent.RegisterButtonClicked -> viewModelScope.launch {
                emitEffect(LoginEffect.NavToRegisterFragment)
            }
        }
    }

}
