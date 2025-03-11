package com.example.homeworktbc.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homeworktbc.data.local.dao.RemoteKeysDao
import com.example.homeworktbc.data.local.dao.UserDao
import com.example.homeworktbc.data.local.entity.RemoteKeyEntity
import com.example.homeworktbc.data.local.entity.User

@Database(
    entities = [User::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}