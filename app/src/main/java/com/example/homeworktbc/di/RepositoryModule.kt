package com.example.homeworktbc.di

import com.example.homeworktbc.data.repository.AccountsRepositoryImpl
import com.example.homeworktbc.domain.repository.AccountsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAccountRepository(impl : AccountsRepositoryImpl) : AccountsRepository

}