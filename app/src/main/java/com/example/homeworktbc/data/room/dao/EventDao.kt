package com.example.homeworktbc.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homeworktbc.data.room.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: List<EventEntity>)

    @Query("SELECT * FROM events ORDER BY id DESC")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Delete
    suspend fun deleteEvents(events: List<EventEntity>)

    @Query("DELETE FROM events WHERE id IN (:eventIds)")  // Make sure `id` is the correct column
    suspend fun deleteEventsByIds(eventIds: List<Int>)  // Delete by event IDs
}
