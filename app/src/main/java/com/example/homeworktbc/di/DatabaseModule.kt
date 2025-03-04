package com.example.homeworktbc.di

import android.content.Context
import androidx.room.Room
import com.example.homeworktbc.data.local.room.dao.AttendedEventDao
import com.example.homeworktbc.data.local.room.dao.EventDao
import com.example.homeworktbc.data.local.room.dataBase.EventDatabase
import com.example.homeworktbc.domain.modele.AttendedEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): EventDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            EventDatabase::class.java,
            "event_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEventDao(appDatabase: EventDatabase): EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideAttendedEvents(appDatabase: EventDatabase): AttendedEventDao {
        return appDatabase.attendedEventDao()
    }

}
