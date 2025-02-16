package com.example.homeworktbc.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.homeworktbc.data.dao.RemoteKeysDao
import com.example.homeworktbc.data.dao.UserDao
import com.example.homeworktbc.data.database.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): RoomDb {
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDb::class.java, "App.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteKeyDao(database : RoomDb) : RemoteKeysDao {
        return database.remoteKeysDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(database : RoomDb) : UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) : DataStore<Preferences> {
        return context.dataStore
    }
}