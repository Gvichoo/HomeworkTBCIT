package com.example.homeworktbc.fragmentRegister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.authRetro.AuthenticationClient
import com.example.homeworktbc.enumClass.AuthorizationError
import com.example.homeworktbc.authRetro.AuthRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val authClient = AuthenticationClient()

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
            try {
                _registerState.value = RegisterState(loading = true)

                val response: Response<RegisterResponse> = authClient.register(AuthRequest(email, password))

                _registerState.value = RegisterState(loading = false)

                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse?.token != null) {
                        _registerState.value = RegisterState(success = "Registration successful")
                    } else {
                        _registerState.value = RegisterState(error = AuthorizationError.NoTokenError)
                    }
                } else {
                    _registerState.value = RegisterState(error = AuthorizationError.RegistrationFailedError)
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState(error = AuthorizationError.ExceptionHappened)
            }
        }
    }
}

sealed interface Result<E : Error,Success> {

    data class Success<E : Error ,Success>(
        val result: Success
    ) : Result<E,Success>

    data class IsLoading<E : Error,Success>(
        val isLoading: Boolean
    ) : Result<E,Success>

    data class Failed<E : Error,Success>(
        val error: E
    ) : Result<E,Success>

}




interface Error


