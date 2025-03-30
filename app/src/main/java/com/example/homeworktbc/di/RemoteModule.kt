package com.example.homeworktbc.di

import com.example.homeworktbc.data.helper.ApiHelper
import com.example.homeworktbc.data.remote.service.AccountsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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
class RemoteModule {

    private  val baseUrl = "https://run.mocky.io/v3/d689fe3e-6faf-446a-9896-c538de3449fa/"

    private val json by lazy {
        Json { ignoreUnknownKeys = true }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

            .build()
    }
    @Provides
    @Singleton
    fun provideAccountsApiService(retrofit: Retrofit): AccountsApiService {
        return retrofit.create(AccountsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(): ApiHelper {
        return ApiHelper()
    }

}