package com.example.homeworktbc.di

import com.example.homeworktbc.BuildConfig
import com.example.homeworktbc.data.remote.api.LogInApi
import com.example.homeworktbc.data.remote.api.RegisterApi
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


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
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
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRegisterApi(retrofit : Retrofit) : RegisterApi {
        return retrofit.create(RegisterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLogInApi(retrofit : Retrofit) : LogInApi {
        return retrofit.create(LogInApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit : Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

}
