package com.example.homeworktbc.domain.core

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Failure(val error: ValidationErrors) : ValidationResult()
}