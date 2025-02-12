package com.example.homeworktbc.paging.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey
    val userId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
