package com.example.homeworktbc.data.resource


sealed class Results<out T> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Failed(val error: Throwable) : Results<Nothing>()
    object Loading : Results<Nothing>()
}