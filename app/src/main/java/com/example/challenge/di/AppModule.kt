package com.example.challenge.di

import com.example.challenge.data.service.connection.ConnectionsService
import com.example.challenge.data.service.log_in.LogInService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    fun provideOkhttpClient(logging:HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().build()
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }


//
//    @Singleton
//    @Provides
//    fun provideHandleResponse(): HandleResponse {
//        return HandleResponse()
//    }

    @Singleton
    @Provides
    fun provideLogInService(retrofit: Retrofit): LogInService {
        return retrofit.create(LogInService::class.java)
    }

    @Singleton
    @Provides
    fun provideConnectionsService(retrofit: Retrofit): ConnectionsService {
        return retrofit.create(ConnectionsService::class.java)
    }
}