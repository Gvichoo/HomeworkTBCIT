package com.example.homeworktbc.presentation.fragmentRegister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.resource.AuthorizationError
import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.data.resource.Results
import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.domain.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    fun registerUser(email: String, password: String, passwordRepeated: String) {
        if (email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()) {
            _registerState.value = RegisterState(error = AuthorizationError.NoFieldsFilledError)
            return
        }

        if (password.length < 8) {
            _registerState.value = RegisterState(error = AuthorizationError.InvalidPassword)
            return
        }

        if (password != passwordRepeated) {
            _registerState.value = RegisterState(error = AuthorizationError.PasswordMissMatch)
            return
        }

        viewModelScope.launch {
            _registerState.value = RegisterState(loading = true)

            val result = handleHttpRequest {
                registerRepository.register(AuthRequest(email, password))
            }

            _registerState.value = when (result) {
                is Results.Success -> {
                    RegisterState(success = "Registration successful!")
                }
                is Results.Failed -> {
                    RegisterState(error = AuthorizationError.RegistrationFailedError)
                }
                else -> {
                    RegisterState(error = AuthorizationError.RegistrationFailedError)
                }
            }
        }
    }
}


