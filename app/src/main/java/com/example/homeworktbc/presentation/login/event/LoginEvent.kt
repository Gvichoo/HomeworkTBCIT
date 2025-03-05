package com.example.homeworktbc.presentation.login.event

import android.content.Context

sealed class LoginEvent {
    data class LoginButtonClicked(val email: String, val password: String,val rememberMe : Boolean,val context: Context) : LoginEvent()
    data object SignUpClicked : LoginEvent()


}