package com.example.homeworktbc.fragmentLogin

import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.App
import com.PreferenceKeys
import com.example.DataStoreManager
import com.example.dataStore
import com.example.homeworktbc.authRetro.AuthenticationClient
import com.example.homeworktbc.enumClass.AuthorizationError
import com.example.homeworktbc.fragmentRegister.Result
import com.example.homeworktbc.authRetro.AuthRequest
import com.example.homeworktbc.fragmentRegister.handleHttpRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val authClient = AuthenticationClient()

    fun loginUser(email: String, password: String, onResult: (Result<AuthorizationError, String>) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            onResult(Result.Failed(AuthorizationError.NoFieldsFilledError))
            return
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
