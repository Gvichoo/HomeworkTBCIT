package com.example.homeworktbc.di.repository

import com.example.homeworktbc.data.resource.Resource
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun register(email: String, password: String): Flow<Resource<Boolean>>
}