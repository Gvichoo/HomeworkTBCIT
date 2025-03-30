package com.example.homeworktbc.di

import com.example.homeworktbc.domain.repository.AccountsRepository
import com.example.homeworktbc.domain.usecase.GetAccountsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAccountsUseCase(accountsRepository: AccountsRepository): GetAccountsUseCase {
        return GetAccountsUseCase(accountsRepository)
    }

}