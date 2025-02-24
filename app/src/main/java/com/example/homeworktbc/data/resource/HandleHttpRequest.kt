package com.example.homeworktbc.data.resource

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Resource<T> {
    return try {
        val response = apiCall.invoke()

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
            Resource.Failed("No data received.")
        } else {
            Resource.Failed("HTTP Error: ${response.code()} - ${response.message()}")
        }
    } catch (throwable: Throwable) {
        val error = when (throwable) {
            is IOException -> "Network error. Please check your connection."
            is HttpException -> "Server error: ${throwable.code()} - ${throwable.message()}"
            is IllegalStateException -> "Unexpected response format."
            else -> "Unknown error: ${throwable.message}"
        }
        Resource.Failed(error)
    }
}