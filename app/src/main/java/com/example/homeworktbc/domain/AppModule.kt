package com.example.homeworktbc.domain

import com.example.homeworktbc.data.repository.LogInRepositoryImpl
import com.example.homeworktbc.data.repository.RegisterRepositoryImpl
import com.example.homeworktbc.di.repository.LogInRepository
import com.example.homeworktbc.di.repository.RegisterRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Binds RegisterRepository to its implementation
    @Binds
    abstract fun bindRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository

    // Binds LogInRepository to its implementation
    @Binds
    abstract fun bindLogInRepository(
        impl: LogInRepositoryImpl
    ): LogInRepository
}
