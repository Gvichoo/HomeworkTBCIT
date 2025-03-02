package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.domain.core.Resource
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun register(email: String, password: String): Flow<Resource<Boolean>>
}