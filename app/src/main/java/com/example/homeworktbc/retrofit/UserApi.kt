package com.example.homeworktbc.retrofit

data class UserApi(
    val id: Int,
    val avatar: String?,
    val first_name: String,
    val last_name: String,
    val about: String?,
    val activation_status: Int
)