package com.example.homeworktbc.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun <T> saveValue(key: Preferences.Key<T>, value: T)

    fun <T> readValue(key: Preferences.Key<T>): Flow<T>

    suspend fun <T> removeByKey(key: Preferences.Key<T>)

}