package com.example.homeworktbc.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.homeworktbc.data.local.datastore.PreferenceKeys.EMAIL_KEY
import com.example.homeworktbc.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    DataStoreRepository {
    override suspend fun saveValue(email : String) {
        dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    override fun readValue(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }
    }

    override suspend fun removeByKey() {
        dataStore.edit { preferences ->
            preferences.remove(EMAIL_KEY)
        }
    }
}