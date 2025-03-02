package com.example.homeworktbc.data.repository

import androidx.datastore.core.DataStore
import com.example.homeworktbc.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.homeworktbc.data.datastore.PreferenceKeys.languages
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    DataStoreRepository {
    override suspend fun saveValue(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override fun readValue(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data.map {
            it[key] ?: ""
        }
    }

    override suspend fun removeByKey(key: Preferences.Key<String>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun saveLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[languages] = language
        }
    }

    override fun readLanguage(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[languages] ?: "en"
        }
    }
}