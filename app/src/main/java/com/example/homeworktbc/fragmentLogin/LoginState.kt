package com.example.homeworktbc.fragmentLogin

import com.example.homeworktbc.enumClass.AuthorizationError

data class LoginState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: AuthorizationError? = null
)