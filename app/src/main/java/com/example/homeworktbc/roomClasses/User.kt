package com.example.homeworktbc.roomClasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int,
    val firstName: String,
    val lastName: String,
    val profileImage: String?,
    val about: String?,
    val activation_status: Int
)