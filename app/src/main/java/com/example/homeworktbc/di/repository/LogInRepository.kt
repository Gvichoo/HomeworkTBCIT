package com.example.homeworktbc.di.repository

import com.example.homeworktbc.data.resource.Resource
import kotlinx.coroutines.flow.Flow

interface LogInRepository {
    fun login(email: String, password: String): Flow<Resource<Boolean>>
}