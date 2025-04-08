package com.example.homeworktbc.domain.usecase.validation

import com.example.homeworktbc.domain.core.ValidationErrors
import com.example.homeworktbc.domain.core.ValidationResult
import java.util.regex.Pattern

class PasswordValidationUseCase {

    private val passwordRegex = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).+$")

    operator fun invoke(password: String): ValidationResult {
        return when {
            password.isEmpty() -> ValidationResult.Failure(ValidationErrors.EMPTY)
            password.length < 8 -> ValidationResult.Failure(ValidationErrors.INVALID_PASSWORD)
            !passwordRegex.matcher(password).matches() ->
                ValidationResult.Failure(ValidationErrors.PASSWORD_WEAK)
            else -> ValidationResult.Success
        }
    }
}