package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.helper.ApiHelper
import com.example.homeworktbc.data.mapper.toDomain
import com.example.homeworktbc.data.remote.service.AccountsApiService
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.Account
import com.example.homeworktbc.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val serviceApi : AccountsApiService,
    private val apiHelper : ApiHelper
) : AccountsRepository{
    override suspend fun getAccounts(): Flow<Resource<List<Account>>> {
        return apiHelper.handleHttpRequest(
            apiCall = {serviceApi.getAccounts()},
            mapToDomain = { accountDtoList -> accountDtoList.map { it.toDomain() } }
        )
    }
}