package com.example.homeworktbc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val profile: String? = null,
    @SerialName("post_date")
    val postDate: Long
)