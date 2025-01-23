package com.example.homeworktbc.fragmentRegister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.authRetro.AuthenticationClient
import com.example.homeworktbc.enumClass.AuthorizationError
import com.example.homeworktbc.authRetro.AuthRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val authClient = AuthenticationClient()

    fun registerUSer(email: String, password: String,passwordRepeated : String,onResult: (Result<AuthorizationError,String>) -> Unit) {


        if (email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()) {
            onResult(Result.Failed(AuthorizationError.NoFieldsFilledError))
            return
        }

        if(password.length < 8){
            onResult(Result.Failed(AuthorizationError.InvalidPassword))
            return
        }

        if (password != passwordRepeated) {
            onResult(Result.Failed(AuthorizationError.PasswordMissMatch))
            return
        }
        viewModelScope.launch {
            try {
                onResult(Result.IsLoading(true))
                val response: Response<RegisterResponse> = authClient.register(AuthRequest(email, password))
                onResult(Result.IsLoading(false))

                if (response.isSuccessful) {

                    val registerResponse = response.body()
                    if (registerResponse?.token != null) {
                        onResult(Result.Success("Registration successful"))
                    } else {
                        onResult(Result.Failed(AuthorizationError.NoTokenError))
                    }
                } else {
                    onResult(Result.Failed(AuthorizationError.RegistrationFailedError))
                }
            } catch (e: Exception) {
                onResult(Result.Failed(AuthorizationError.ExceptionHappened))
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


