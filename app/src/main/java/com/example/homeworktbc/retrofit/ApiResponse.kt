package com.example.homeworktbc.retrofit

data class ApiResponse (
    val status: Boolean,
    val users: List<UserApi>
)