package com.example.homeworktbc

import java.util.UUID

data class Items(
    val id:  String = UUID.randomUUID().toString(),
    val name: String,
    val image: Int,
    val color: String,
    val price: String,
    val qtyNum: Int
)
