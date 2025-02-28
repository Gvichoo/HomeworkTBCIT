package com.example.homeworktbc.data.api

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val lat: Double,
    val lan: Double,
    val title: String,
    val address: String
)
