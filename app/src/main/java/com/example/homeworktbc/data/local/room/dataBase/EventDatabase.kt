package com.example.homeworktbc.data.local.room.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homeworktbc.data.local.room.dao.AttendedEventDao
import com.example.homeworktbc.data.local.room.dao.EventDao
import com.example.homeworktbc.data.local.room.entity.AttendedEventEntity
import com.example.homeworktbc.data.local.room.entity.EventEntity

@Database(entities = [EventEntity::class, AttendedEventEntity::class], version = 3, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    abstract fun attendedEventDao() : AttendedEventDao
}

