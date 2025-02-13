package com.example.homeworktbc.di

import com.example.homeworktbc.data.remote.api.MyApi
import com.example.homeworktbc.data.repository.LoginRepositoryImpl
import com.example.homeworktbc.domain.repository.LoginRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        @Singleton
        fun provideMyApi(): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(MyApi::class.java)
        }
    }
}
