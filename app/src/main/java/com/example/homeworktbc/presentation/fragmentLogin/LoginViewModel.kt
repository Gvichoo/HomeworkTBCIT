package com.example.homeworktbc.presentation.fragmentLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.DataStoreManager
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.data.enumClass.AuthorizationError
import com.example.homeworktbc.domain.repository.LoginRepository
import com.example.homeworktbc.presentation.fragmentRegister.Result
import com.example.homeworktbc.presentation.fragmentRegister.handleHttpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private fun saveEmailToDataStore(email: String) {
        viewModelScope.launch {
            val emailKey = PreferenceKeys.email
            DataStoreManager.saveValue(emailKey, email)
        }
    }

    fun loginUser(email: String, password: String, rememberMe: Boolean, onResult: (Result<AuthorizationError, String>) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            onResult(Result.Failed(AuthorizationError.NoFieldsFilledError))
            return
        }

        if (rememberMe) {
            saveEmailToDataStore(email)
        }

        viewModelScope.launch {
            val result = handleHttpRequest {
                loginRepository.login(email, password)
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