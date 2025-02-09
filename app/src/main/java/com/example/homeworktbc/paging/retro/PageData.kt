package com.example.homeworktbc.paging.retro

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageData(
    val page : Int,
    @SerialName("per_page")
    val perPage : Int?,
    val total : Int,
    @SerialName("total_pages")
    val totalPages : Int?,
    val data : List<UserData>
)

