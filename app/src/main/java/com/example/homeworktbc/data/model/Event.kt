package com.example.homeworktbc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: Int,
    val name: String,
    val image: String,
    val organizer: String,
    val date: String,
    val info : String,
    val price : String
)