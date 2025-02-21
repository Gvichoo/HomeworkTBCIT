package com.example.homeworktbc.presentation.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _signUpState = MutableStateFlow<Resource<Boolean>>(Resource.Loading())
    val signUpState: StateFlow<Resource<Boolean>> = _signUpState

    fun validateInputsAndSignUp(email: String, password: String, repeatedPassword: String) {
        if (validateInputs(email, password, repeatedPassword)) {
            signUpUser(email, password)
        }
    }

    private fun validateInputs(email: String, password: String, repeatedPassword: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()) {
            _signUpState.value = Resource.Error("All fields are required!")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _signUpState.value = Resource.Error("Please enter a valid email address!")
            return false
        }

        if (password.length < 8) {
            _signUpState.value = Resource.Error("Password must be at least 8 characters!")
            return false
        }

        if (password != repeatedPassword) {
            _signUpState.value = Resource.Error("Passwords do not match!")
            return false
        }

        return true
    }

    private fun signUpUser(email: String, password: String) {
        _signUpState.value = Resource.Loading()

        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _signUpState.value = Resource.Success(true)
                    } else {
                        _signUpState.value = Resource.Error("Sign-up failed: ${task.exception?.message}")
                    }
                }
        }
    }
}