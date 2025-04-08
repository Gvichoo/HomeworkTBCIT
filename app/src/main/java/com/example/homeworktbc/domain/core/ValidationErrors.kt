package com.example.homeworktbc.domain.core

enum class ValidationErrors(val message: String) {
    EMPTY("Inputs are Empty!"),
    INVALID_FORMAT("Invalid email format!"),
    INVALID_PASSWORD("Password should be at least 8 characters!"),
    PASSWORD_WEAK("Password must contain at least one letter and one digit!"),
    PASSWORDS_DO_NOT_MATCH("Passwords do not match!")
}