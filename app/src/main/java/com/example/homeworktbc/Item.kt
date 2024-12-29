package com.example.homeworktbc

data class Item(
    val id: Int,
    val orderNumber: String,
    val quantity: Int,
    val subTotal: Double,
    val date: Long,
    var status: String
)
