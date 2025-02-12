package com.example.homeworktbc.fragmentLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.datastore.DataStoreManager
import com.example.homeworktbc.datastore.PreferenceKeys
import com.example.homeworktbc.authRetro.AuthenticationClient
import com.example.homeworktbc.enumClass.AuthorizationError
import com.example.homeworktbc.fragmentRegister.Result
import com.example.homeworktbc.authRetro.AuthRequest
import com.example.homeworktbc.fragmentRegister.handleHttpRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val authClient = AuthenticationClient()

    private fun saveEmailToDataStore(email: String) {
        viewModelScope.launch {
            val emailKey = PreferenceKeys.email
            DataStoreManager.saveValue(emailKey, email)
        }
    }

    fun loginUser(email: String, password: String,rememberMe: Boolean, onResult: (Result<AuthorizationError, String>) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            onResult(Result.Failed(AuthorizationError.NoFieldsFilledError))
            return
        }

        if (rememberMe) {
            saveEmailToDataStore(email)
        }


        viewModelScope.launch {
            val result = handleHttpRequest {
                authClient.login(AuthRequest(email, password))
            }

            when (result) {
                is Result.Success -> {
                    onResult(Result.Success(result = "Login successful"))
                }
                is Result.Failed -> {
                    when (result.error) {
                        AuthorizationError.NoFieldsFilledError -> {
                        }
                        else -> {
                            onResult(Result.Failed(error = AuthorizationError.LoginFailedError))
                        }
                    }
                }
                is Result.IsLoading -> {
                        onResult(Result.IsLoading(isLoading = true))
                }
            }
        }
    }
}
