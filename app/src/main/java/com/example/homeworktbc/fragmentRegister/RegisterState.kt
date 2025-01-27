package com.example.homeworktbc.fragmentRegister

import com.example.homeworktbc.enumClass.AuthorizationError

data class RegisterState(
    val loading: Boolean = false,
    val success: String? = null,
    val error: AuthorizationError? = null
)