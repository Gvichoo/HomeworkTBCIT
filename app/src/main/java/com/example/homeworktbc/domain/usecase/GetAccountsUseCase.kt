package com.example.homeworktbc.domain.usecase

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.Account
import com.example.homeworktbc.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Account>>> {
        return accountsRepository.getAccounts()
    }
}