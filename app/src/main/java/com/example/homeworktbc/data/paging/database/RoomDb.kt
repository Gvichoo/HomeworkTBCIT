package com.example.homeworktbc.data.paging.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homeworktbc.data.paging.dao.RemoteKeysDao
import com.example.homeworktbc.data.paging.dao.UserDao
import com.example.homeworktbc.data.paging.entity.RemoteKeyEntity
import com.example.homeworktbc.data.paging.entity.User

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