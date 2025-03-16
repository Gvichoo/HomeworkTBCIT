package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.local.datastore.PreferenceKeys.EMAIL_KEY
import com.example.homeworktbc.data.remote.api.LogInApi
import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.core.handleHttpRequest
import com.example.homeworktbc.domain.model.login.LoginResponse
import com.example.homeworktbc.domain.repository.DataStoreRepository
import com.example.homeworktbc.domain.repository.LoginRepository
import com.example.homeworktbc.presentation.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val apiService: LogInApi,
    private val dataStoreRepository: DataStoreRepository
) : LoginRepository {

    override suspend fun login(email: String, password: String, rememberMe: Boolean): Flow<Resource<LoginResponse>> {
        return handleHttpRequest(
            apiCall = { apiService.login(AuthRequest(email, password)) },
            mapToDomain = { it.toDomain() }
        ).also {
            if (rememberMe) {
                saveEmailToDataStore(email)
            }
        }
    }
    private suspend fun saveEmailToDataStore(email: String) {
        dataStoreRepository.saveValue(EMAIL_KEY, email)
    }
}


