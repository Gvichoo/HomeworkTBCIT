package com.example.homeworktbc.presentation.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    private val _signUpState = MutableStateFlow<Resource<Boolean>>(Resource.Loading())
    val signUpState: StateFlow<Resource<Boolean>> = _signUpState

    fun validateInputsAndSignUp(email: String, password: String, repeatedPassword: String) {
        if (validateInputs(email, password, repeatedPassword)) {
            signUpUser(email, password)
        }
    }

    private fun validateInputs(email: String, password: String, repeatedPassword: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()) {
            _signUpState.value = Resource.Failed("All fields are required!")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _signUpState.value = Resource.Failed("Please enter a valid email address!")
            return false
        }

        if (password.length < 8) {
            _signUpState.value = Resource.Failed("Password must be at least 8 characters!")
            return false
        }

        if (password != repeatedPassword) {
            _signUpState.value = Resource.Failed("Passwords do not match!")
            return false
        }

        return true
    }

    private fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            registerRepository.register(email, password).collect { result ->
                _signUpState.value = result
            }
        }
    }
}