package com.example.homeworktbc.domain.usecase.validation

import com.example.homeworktbc.domain.core.ValidationErrors
import com.example.homeworktbc.domain.core.ValidationResult

class RepeatedPasswordValidationUseCase {

    operator fun invoke(password: String, repeatedPassword: String): ValidationResult {

        return when{
            password != repeatedPassword ->
                ValidationResult.Failure(ValidationErrors.PASSWORDS_DO_NOT_MATCH)

            else -> {
                ValidationResult.Success
            }
        }
    }
}