package com.example.homeworktbc.di

import com.example.homeworktbc.data.repository.AttendedEventRepositoryImpl
import com.example.homeworktbc.data.repository.DataStoreRepositoryImpl
import com.example.homeworktbc.data.repository.EventRepositoryImpl
import com.example.homeworktbc.data.repository.LogInRepositoryImpl
import com.example.homeworktbc.data.repository.RegisterRepositoryImpl
import com.example.homeworktbc.domain.repository.AttendedEventRepository
import com.example.homeworktbc.domain.repository.DataStoreRepository
import com.example.homeworktbc.domain.repository.EventRepository
import com.example.homeworktbc.domain.repository.LogInRepository
import com.example.homeworktbc.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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

    @Binds
    @Singleton
    abstract fun bindsDataStoreRepository(impl : DataStoreRepositoryImpl) : DataStoreRepository

    @Binds
    @Singleton
    abstract fun bindsAttendedEventRepository(impl : AttendedEventRepositoryImpl) : AttendedEventRepository

}