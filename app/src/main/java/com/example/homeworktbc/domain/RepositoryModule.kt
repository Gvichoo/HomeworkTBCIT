package com.example.homeworktbc.domain

import com.example.homeworktbc.data.repository.PostRepositoryImpl
import com.example.homeworktbc.di.repository.StoryRepository
import com.example.homeworktbc.data.repository.StoryRepositoryImpl
import com.example.homeworktbc.di.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindStoryRepository(impl: StoryRepositoryImpl): StoryRepository


    @Binds
    @ViewModelScoped
    abstract fun bindPostRepository(impl: PostRepositoryImpl) : PostRepository
}