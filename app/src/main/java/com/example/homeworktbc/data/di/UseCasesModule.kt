package com.example.homeworktbc.data.di

import com.example.homeworktbc.domain.usecase.validation.EmailValidationUseCase
import com.example.homeworktbc.domain.usecase.validation.PasswordValidationUseCase
import com.example.homeworktbc.domain.usecase.validation.RepeatedPasswordValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideEmailValidationUseCase(): EmailValidationUseCase {
        return EmailValidationUseCase()
    }

    @Provides
    fun providePasswordValidationUseCase(): PasswordValidationUseCase {
        return PasswordValidationUseCase()
    }

    @Provides
    fun provideRepeatedPasswordValidationUseCase(): RepeatedPasswordValidationUseCase {
        return RepeatedPasswordValidationUseCase()
    }

}