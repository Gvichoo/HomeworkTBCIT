package com.example.homeworktbc.data.resource

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Results<T> {
    return try {
        val response = apiCall.invoke()

        if (response.isSuccessful) {
            response.body()?.let {
                Results.Success(it)
            } ?: Results.Failed(Exception("Empty response body"))
        } else {
            Results.Failed(Exception("HTTP Error: ${response.code()} - ${response.message()}"))
        }
    } catch (throwable: Throwable) {
        val error = when (throwable) {
            is IOException -> Exception("Network error. Please check your connection.")
            is HttpException -> Exception("Server error: ${throwable.code()} - ${throwable.message()}")
            is IllegalStateException -> Exception("Unexpected response format.")
            else -> Exception("Unknown error occurred.")
        }
        Results.Failed(error)
    }
}