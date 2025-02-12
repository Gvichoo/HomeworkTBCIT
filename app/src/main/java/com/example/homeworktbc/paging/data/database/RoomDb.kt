package com.example.homeworktbc.paging.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homeworktbc.paging.data.dao.RemoteKeysDao
import com.example.homeworktbc.paging.data.dao.UserDao
import com.example.homeworktbc.paging.data.entity.RemoteKeyEntity
import com.example.homeworktbc.paging.data.entity.User

@Database(
    entities = [User::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getInstance(context: Context): RoomDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RoomDb::class.java, "App.db"
            )
                .build()
    }
}