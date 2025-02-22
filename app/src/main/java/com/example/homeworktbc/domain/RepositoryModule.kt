package com.example.homeworktbc.domain

import com.example.homeworktbc.data.repository.EventRepositoryImpl
import com.example.homeworktbc.data.repository.LogInRepositoryImpl
import com.example.homeworktbc.data.repository.RegisterRepositoryImpl
import com.example.homeworktbc.di.repository.EventRepository
import com.example.homeworktbc.di.repository.LogInRepository
import com.example.homeworktbc.di.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository

    @Binds
    abstract fun bindLogInRepository(
        impl: LogInRepositoryImpl
    ): LogInRepository

    @Binds
    abstract fun bindEventRepository(impl: EventRepositoryImpl): EventRepository

}