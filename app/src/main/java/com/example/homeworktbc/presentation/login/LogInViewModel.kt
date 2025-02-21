package com.example.homeworktbc.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.LogInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInRepository: LogInRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<Boolean>>(Resource.Loading())
    val loginState: StateFlow<Resource<Boolean>> = _loginState

    fun validateInputsAndLogin(email: String, password: String) {
        if (validateInputs(email, password)) {
            loginUser(email, password)
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            _loginState.value = Resource.Error("All fields are required!")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _loginState.value = Resource.Error("Please enter a valid email address!")
            return false
        }

        if (password.length < 8) {
            _loginState.value = Resource.Error("Password must be at least 8 characters!")
            return false
        }

        return true
    }

    private fun loginUser(email: String, password: String) {
        _loginState.value = Resource.Loading()

        viewModelScope.launch {
            logInRepository.login(email, password).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _loginState.value = Resource.Success(true)
                    }
                    is Resource.Error -> {
                        _loginState.value = Resource.Error(resource.message ?: "Login failed")
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }
}