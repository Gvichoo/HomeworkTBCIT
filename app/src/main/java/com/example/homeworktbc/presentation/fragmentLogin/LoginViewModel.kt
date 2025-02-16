package com.example.homeworktbc.presentation.fragmentLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.data.resource.AuthorizationError
import com.example.homeworktbc.data.remote.response.LoginResponse
import com.example.homeworktbc.data.resource.Results
import com.example.homeworktbc.data.resource.handleHttpRequest
import com.example.homeworktbc.domain.repository.DataStoreRepository
import com.example.homeworktbc.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<Results<LoginResponse>?>(null)
    val loginState: StateFlow<Results<LoginResponse>?> = _loginState

    fun saveEmailToDataStore(email: String) {
        viewModelScope.launch {
            val emailKey = PreferenceKeys.email
            dataStoreRepository.saveValue(emailKey, email)
        }
    }

    fun loginUser(email: String, password: String, rememberMe: Boolean ) {
        if (email.isEmpty() || password.isEmpty()) {
            AuthorizationError.LoginFailedError
            return
        }

        if (rememberMe) {
            saveEmailToDataStore(email)
        }

        viewModelScope.launch {
            _loginState.value = Results.Loading

            val result = handleHttpRequest {
                loginRepository.login(email, password)
            }

            _loginState.value = when (result) {
                is Results.Success -> Results.Success(result.data)
                is Results.Failed -> Results.Failed(result.error)
                else -> Results.Failed(Exception("Unknown error occurred"))
            }
        }
    }
}
