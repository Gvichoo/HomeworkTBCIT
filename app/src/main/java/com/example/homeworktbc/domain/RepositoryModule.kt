package com.example.homeworktbc.domain

import com.example.homeworktbc.data.repository.PostRepositoryImpl
import com.example.homeworktbc.di.repository.StoryRepository
import com.example.homeworktbc.data.repository.StoryRepositoryImpl
import com.example.homeworktbc.di.repository.PostRepository
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
    abstract fun bindStoryRepository(impl: StoryRepositoryImpl): StoryRepository


    @Binds
    @Singleton
    abstract fun bindPostRepository(impl: PostRepositoryImpl) : PostRepository
}