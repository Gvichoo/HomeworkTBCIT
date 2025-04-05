package com.example.homeworktbc.presentation.fragmentRegister

import com.example.homeworktbc.R
import com.example.homeworktbc.domain.core.AuthFieldErrorType

fun AuthFieldErrorType.mapToStringResource(): Int {
    return when (this) {
        AuthFieldErrorType.EMPTY -> R.string.fields_must_be_filled
        AuthFieldErrorType.TooShort -> R.string.invalid_password
        AuthFieldErrorType.InvalidFormat -> R.string.invalid_password
        AuthFieldErrorType.PasswordsDoNotMatch -> R.string.passwords_do_not_match
    }
}