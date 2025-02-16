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
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = "https://reqres.in/api/"

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        @OptIn(ExperimentalSerializationApi::class)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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




//
//    @Binds
//    @Singleton
//    abstract fun bindLoginRepository(
//        loginRepositoryImpl: LoginRepositoryImpl
//    ): LoginRepository
//
//    companion object {
//        @OptIn(ExperimentalSerializationApi::class)
//        @Provides
//        @Singleton
//        fun provideMyApi(): MyApi {
//            return Retrofit.Builder()
//                .baseUrl("https://reqres.in")
//                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//                .build()
//                .create(MyApi::class.java)
//        }
//    }
}
