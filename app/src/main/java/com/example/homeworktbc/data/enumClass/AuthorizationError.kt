package com.example.homeworktbc.data.enumClass

import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.fragmentRegister.Error

enum class AuthorizationError(val errorMessageResource : Int) : Error {
    NoFieldsFilledError(R.string.fields_must_be_filled),
    NoTokenError(R.string.no_token_received),
    RegistrationFailedError(R.string.registration_failed),
    ExceptionHappened(R.string.exception_happened),
    PasswordMissMatch(R.string.passwords_do_not_match),
    InvalidPassword(R.string.invalid_password),
    LoginFailedError(R.string.login_failed)
}