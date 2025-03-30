package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {
    suspend fun getAccounts() : Flow<Resource<List<Account>>>
}