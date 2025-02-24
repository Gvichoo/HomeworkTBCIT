package com.example.homeworktbc.domain

import com.example.homeworktbc.data.api.PostApiService
import com.example.homeworktbc.data.api.StoryApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StoryRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PostRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL_1 = "https://run.mocky.io/v3/"
    private const val BASE_URL_2 = "https://run.mocky.io/v3/"

    private val json by lazy {
        Json { ignoreUnknownKeys = true }
    }

    @Provides
    @Singleton
    @StoryRetrofit
    fun provideStoryRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_1)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }


    @Provides
    @Singleton
    @StoryRetrofit
    fun provideStoryApiService(@StoryRetrofit retrofit: Retrofit): StoryApiService {
        return retrofit.create(StoryApiService::class.java)
    }

    @Provides
    @Singleton
    @PostRetrofit
    fun providePostRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_2)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    @PostRetrofit
    fun providePostApiService(@PostRetrofit retrofit: Retrofit): PostApiService {
        return retrofit.create(PostApiService::class.java)
    }


}