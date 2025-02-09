package com.example

import android.content.Context
import android.system.Os.remove
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreManager {
    suspend fun saveValue(key: Preferences.Key<String>, value: String) {
        Log.d("DataStore", "Saving value: $value")
        App.context?.dataStore?.edit { preferences ->
            preferences[key] = value
        }
    }


    fun readValue(key: Preferences.Key<String>): Flow<String>? {
        return App.context?.dataStore?.data?.map {
            it[key] ?: ""
        }
    }

    suspend fun removeByKey(context: Context, key: Preferences.Key<String>) {
        context.dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }
}