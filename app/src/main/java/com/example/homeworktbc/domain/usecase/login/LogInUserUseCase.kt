package com.example.homeworktbc.domain.usecase.login

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.LoginSession
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LogInUserUseCase {
    suspend operator fun invoke(
        email: String,
        password: String,
        rememberMe: Boolean
    ): Flow<Resource<LoginSession>>
}

class LogInUserUseCaseImpl @Inject constructor(
    private val loginRepository: LogInRepository,
    private val savePreferenceValueUseCase: SavePreferenceValueUseCase,
) : LogInUserUseCase {
    override suspend operator fun invoke(
        email: String,
        password: String,
        rememberMe: Boolean
    ): Flow<Resource<LoginSession>> {
        return loginRepository.login(email, password, rememberMe)
            .handleSuccess { resource ->
                savePreferenceValueUseCase(key = AppPreferenceKeys.EMAIL_KEY, value = email)
                if (rememberMe) {
                    savePreferenceValueUseCase(
                        key = AppPreferenceKeys.TOKEN_KEY,
                        value = resource.token
                    )
                }
            }
    }
}