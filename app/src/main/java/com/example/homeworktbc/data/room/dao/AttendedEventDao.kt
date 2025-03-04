package com.example.homeworktbc.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homeworktbc.data.room.entity.AttendedEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendedEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: AttendedEventEntity)


    @Query("SELECT * FROM attended_events ORDER BY id DESC")
    fun getAllEvents(): Flow<List<AttendedEventEntity>>

    @Query("DELETE FROM attended_events WHERE id = :eventIds")
    suspend fun deleteEventsByIds(eventIds: Int)

}