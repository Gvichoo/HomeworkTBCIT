package com.example.homeworktbc.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homeworktbc.data.dao.RemoteKeysDao
import com.example.homeworktbc.data.dao.UserDao
import com.example.homeworktbc.data.entity.RemoteKeyEntity
import com.example.homeworktbc.data.entity.User

@Database(
    entities = [User::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}