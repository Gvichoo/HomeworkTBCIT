package com.example.homeworktbc.presentation.fragmentRegister

import com.example.homeworktbc.data.resource.AuthorizationError

data class RegisterState(
    val loading: Boolean = false,
    val success: String? = null,
    val error: AuthorizationError? = null
)