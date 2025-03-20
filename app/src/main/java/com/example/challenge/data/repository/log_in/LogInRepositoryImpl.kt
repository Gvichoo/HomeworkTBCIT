package com.example.challenge.data.repository.log_in

import com.example.challenge.data.mapper.log_in.toDomain
import com.example.challenge.domain.core.Resource
import com.example.challenge.data.remote.service.log_in.LogInService
import com.example.challenge.domain.core.handleHttpRequest
import com.example.challenge.domain.model.log_in.GetToken
import com.example.challenge.domain.repository.log_in.LogInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LogInRepositoryImpl @Inject constructor(
    private val logInService: LogInService,
) : LogInRepository {
    override suspend fun logIn(email: String, password: String): Flow<Resource<GetToken>> {
        return handleHttpRequest(
            apiCall = {logInService.logIn(email = email,password = password)},
            mapToDomain = { it.toDomain() }
        )
    }
}