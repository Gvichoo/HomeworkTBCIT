package com.example.homeworktbc.fragmentLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.models.AuthRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> get() = _loginResult

    private val serviceLogin = ServiceLogin()

    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _loginResult.value = false
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = serviceLogin.login(AuthRequest(email, password))

                withContext(Dispatchers.Main) {
                    _loginResult.value = true
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loginResult.value = false
                }
            }
        }
    }
}
