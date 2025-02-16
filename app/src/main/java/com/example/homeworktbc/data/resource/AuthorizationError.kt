package com.example.homeworktbc.data.resource

import com.example.homeworktbc.R

enum class AuthorizationError(val errorMessageResource : Int) {
    NoFieldsFilledError(R.string.fields_must_be_filled),
    NoTokenError(R.string.no_token_received),
    RegistrationFailedError(R.string.registration_failed),
    ExceptionHappened(R.string.exception_happened),
    PasswordMissMatch(R.string.passwords_do_not_match),
    InvalidPassword(R.string.invalid_password),
    LoginFailedError(R.string.login_failed)
}