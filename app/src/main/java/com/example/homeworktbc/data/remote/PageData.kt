package com.example.homeworktbc.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageData (
    var id : Int,
    var cover : String,
    var price : String,
    var title : String,
    var location : String,
    @SerialName("reaction_count")
    var reactionCount : Int,
    var rate : Float?
)


