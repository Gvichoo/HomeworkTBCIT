package com.example.homeworktbc.di

import com.example.homeworktbc.data.datastore.DataStoreRepositoryImpl
import com.example.homeworktbc.data.repository.HomeRepositoryImpl
import com.example.homeworktbc.data.repository.LoginRepositoryImpl
import com.example.homeworktbc.data.repository.RegisterRepositoryImpl
import com.example.homeworktbc.domain.repository.DataStoreRepository
import com.example.homeworktbc.domain.repository.HomeRepository
import com.example.homeworktbc.domain.repository.LoginRepository
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
    @Singleton
    abstract fun bindsLoginRepository(impl : LoginRepositoryImpl) : LoginRepository


    @Binds
    @Singleton
    abstract fun bindsRegisterRepository(impl : RegisterRepositoryImpl) : RegisterRepository

    @Binds
    @Singleton
    abstract fun bindsHomeRepository(impl : HomeRepositoryImpl) : HomeRepository

    @Binds
    @Singleton
    abstract fun bindsDataStoreRepository(impl : DataStoreRepositoryImpl) : DataStoreRepository

}