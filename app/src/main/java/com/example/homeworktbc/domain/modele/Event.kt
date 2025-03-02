package com.example.homeworktbc.domain.modele

data class Event(
    val id: Int,
    val name: String,
    val image: String,
    val organizer: String,
    val date: String,
    val info : String,
    val price : String
)