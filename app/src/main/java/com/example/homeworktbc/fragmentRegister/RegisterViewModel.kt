package com.example.homeworktbc.fragmentRegister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.models.AuthRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {

    private val _registerResult = MutableStateFlow<Boolean?>(null)
    val registerResult: StateFlow<Boolean?> get() = _registerResult

    private val serviceRegister = ServiceRegister()

    fun registerUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _registerResult.value = false
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = serviceRegister.register(AuthRequest(email, password))

                if (response.token != null) {
                    withContext(Dispatchers.Main) {
                        _registerResult.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _registerResult.value = false
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _registerResult.value = false
                }
            }
        }
    }
}
