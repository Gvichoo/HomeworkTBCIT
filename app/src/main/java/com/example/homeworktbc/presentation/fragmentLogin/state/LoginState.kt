package com.example.homeworktbc.presentation.fragmentLogin.state

data class LoginState (

    val isLoading : Boolean = false,
    val isSuccess : Boolean = false,
    val errorMessage : String? = null

)