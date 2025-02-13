package com.example.homeworktbc.presentation.fragmentRegister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.remote.client.AuthenticationClient
import com.example.homeworktbc.data.enumClass.AuthorizationError
import com.example.homeworktbc.data.remote.request.AuthRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


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
            _registerState.value = RegisterState(loading = true)

            val result = handleHttpRequest {
                authClient.register(AuthRequest(email, password))
            }

            _registerState.value = when (result) {
                is Result.Success -> RegisterState(success = "Registration successful")
                is Result.Failed -> RegisterState(error = AuthorizationError.RegistrationFailedError)
                is Result.IsLoading -> RegisterState(loading = true)
            }
        }
    }
}

sealed interface Result<E : Error, Success> {

    data class Success<E : Error, Success>(
        val result: Success
    ) : Result<E, Success>

    data class IsLoading<E : Error, Success>(
        val isLoading: Boolean
    ) : Result<E, Success>

    data class Failed<E : Error, Success>(
        val error: E
    ) : Result<E, Success>

}

interface Error


suspend fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Result<Error, T> {
    return try {
        val response = apiCall.invoke()

        if (response.isSuccessful) {
            response.body()?.let {
                Result.Success(result = it)
            } ?: Result.Failed(error = object : Error {})
        } else {
            Result.Failed(error = object : Error {})
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> Result.Failed(error = object : Error {})
            is HttpException -> Result.Failed(error = object : Error {})
            is IllegalStateException -> Result.Failed(error = object : Error {})
            else -> Result.Failed(error = object : Error {})
        }
    }
}

