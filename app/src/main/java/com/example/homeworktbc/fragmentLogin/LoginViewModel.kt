package com.example.homeworktbc.fragmentLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.authRetro.AuthenticationClient
import com.example.homeworktbc.enumClass.AuthorizationError
import com.example.homeworktbc.fragmentRegister.Result
import com.example.homeworktbc.authRetro.AuthRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authClient = AuthenticationClient()

    fun loginUser(email: String, password: String, onResult: (Result<AuthorizationError, String>) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            onResult(Result.Failed(AuthorizationError.NoFieldsFilledError))
            return
        }

        viewModelScope.launch {
            try {
                onResult(Result.IsLoading(true))
                val response: Response<LoginResponse> = authClient.login(AuthRequest(email, password))
                onResult(Result.IsLoading(false))

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.token != null) {
                        onResult(Result.Success("Login successful"))
                    } else {
                        onResult(Result.Failed(AuthorizationError.NoTokenError))
                    }
                } else {
                    onResult(Result.Failed(AuthorizationError.LoginFailedError))
                }
            } catch (e: Exception) {
                onResult(Result.Failed(AuthorizationError.ExceptionHappened))
            }
        }
    }
}
