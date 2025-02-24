package com.example.homeworktbc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StoryResponse(
    val id: Int,
    val cover: String,
    val title: String
)
