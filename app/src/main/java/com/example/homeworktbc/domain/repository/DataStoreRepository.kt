package com.example.homeworktbc.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveValue(email: String)
    fun readValue(): Flow<String>
    suspend fun removeByKey()
}