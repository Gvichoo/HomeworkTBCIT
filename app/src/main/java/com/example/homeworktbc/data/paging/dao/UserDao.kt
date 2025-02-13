package com.example.homeworktbc.data.paging.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homeworktbc.data.paging.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, User>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}
