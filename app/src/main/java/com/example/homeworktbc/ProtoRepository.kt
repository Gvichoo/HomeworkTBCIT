package com.example.homeworktbc

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.IOException
import androidx.datastore.dataStoreFile
import com.example.homeworktbc.userSerializer.UserSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class ProtoRepository private constructor(context: Context) {
    private val dataStore: DataStore<MessageUser> = DataStoreFactory.create(
        serializer = UserSerializer,
        produceFile = { context.dataStoreFile("my_file") }
    )

    companion object {
        @Volatile
        private var INSTANCE: ProtoRepository? = null

        fun getInstance(context: Context): ProtoRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ProtoRepository(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        }
    }

    val readProto: Flow<MessageUser> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("error", exception.message.toString())
            } else {
                throw exception
            }
        }

    suspend fun updateValue(firstName: String, lastName: String, email: String) {
        dataStore.updateData { preference ->
            preference.toBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build()
        }
    }
}
