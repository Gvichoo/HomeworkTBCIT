package com.example.homeworktbc.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val organizer: String,
    val date: String,
    val info: String,
    val price: String
)
