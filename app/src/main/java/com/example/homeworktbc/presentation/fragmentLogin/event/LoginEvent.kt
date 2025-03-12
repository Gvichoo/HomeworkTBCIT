package com.example.homeworktbc.presentation.fragmentLogin.event


sealed class LoginEvent {

    data class  LoginButtonClicked(val email: String, val password: String,val rememberMe : Boolean) : LoginEvent()
    data object RegisterButtonClicked : LoginEvent()

}