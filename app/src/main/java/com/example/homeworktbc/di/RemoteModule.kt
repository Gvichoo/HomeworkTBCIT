package com.example.homeworktbc.di

import com.example.homeworktbc.data.remote.api.AuthApi
import com.example.homeworktbc.data.remote.api.UserApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = "https://reqres.in/api/"

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }


    @Provides
    fun provideOkhttpClient(logging:HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(client : OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        @OptIn(ExperimentalSerializationApi::class)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit : Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit : Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

}
