package com.example.homeworktbc.data.room.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homeworktbc.data.room.dao.EventDao
import com.example.homeworktbc.data.room.entity.EventEntity

@Database(entities = [EventEntity::class], version = 2, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}

