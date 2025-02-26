package com.example.homeworktbc.presentation.login

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.DataStoreRepository
import com.example.homeworktbc.di.repository.LogInRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.login.effect.LoginEffect
import com.example.homeworktbc.presentation.login.event.LoginEvent
import com.example.homeworktbc.presentation.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInRepository: LogInRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<LoginState, LoginEvent, LoginEffect>(LoginState()) {





    private fun validateInputsAndLogin(email: String, password: String,rememberMe : Boolean) {
        if (validateInputs(email, password,rememberMe)) {
            loginUser(email, password)
        }
    }


    private fun validateInputs(email: String, password: String,rememberMe: Boolean): Boolean {

        if (email.isEmpty() || password.isEmpty()) {
            viewModelScope.launch {
                emitEffect(LoginEffect.ShowError("All fields are required!"))
            }
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewModelScope.launch {
                emitEffect(LoginEffect.ShowError("Please enter a valid email address!"))
            }
            return false
        }

        if (password.length < 8) {
            viewModelScope.launch {
                emitEffect(LoginEffect.ShowError("Password must be at least 8 characters!"))
            }
            return false
        }
        if (rememberMe){
            saveEmailToDataStore(email)
        }

        return true
    }


    private fun saveEmailToDataStore(email: String) {
        viewModelScope.launch {
            val emailKey = PreferenceKeys.email
            dataStoreRepository.saveValue(emailKey, email)
        }
    }


    private fun loginUser(email: String, password: String) {
        updateState { copy(isLoading = true) }

        viewModelScope.launch {
            logInRepository.login(email, password).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        updateState { copy(isSuccess = true) }
                        emitEffect(LoginEffect.NavToEventsFragment)

                    }
                    is Resource.Failed -> {
                        emitEffect(LoginEffect.ShowError(resource.message ?: "Login failed"))
                    }
                    is Resource.Loading -> {
                        updateState { copy(isLoading = true) }
                    }
                }
            }
        }
    }




    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginButtonClicked -> {
                validateInputsAndLogin(event.email, event.password,event.rememberMe)
            }

            LoginEvent.SignUpClicked -> viewModelScope.launch{
                emitEffect(LoginEffect.NavToRegisterFragment)
            }
        }
    }
}