package com.example.homeworktbc.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveValue(key: Preferences.Key<String>, value: String)
    fun readValue(key: Preferences.Key<String>): Flow<String>
    suspend fun removeByKey(key: Preferences.Key<String>)

    suspend fun saveLanguage(language : String)
    fun readLanguage():Flow<String>
}
