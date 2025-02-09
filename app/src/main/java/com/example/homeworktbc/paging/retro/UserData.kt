package com.example.homeworktbc.paging.retro

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserData (
    val id : Int,
    val email : String,
    @SerialName("first_name")
    val firstName : String?,
    @SerialName("last_name")
    val lastName : String?,
    val avatar : String
)


