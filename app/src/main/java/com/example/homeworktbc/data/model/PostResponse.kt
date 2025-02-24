package com.example.homeworktbc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: Int,
    val images: List<String>? = null,
    val title: String,
    val comments: Int,
    val likes: Int,
    @SerialName("share_content")
    val shareContent: String,
    val owner: Owner
)