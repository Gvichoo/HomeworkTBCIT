package com.example.homeworktbc.data.resource

import android.util.Log
import com.example.homeworktbc.domain.core.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


suspend fun <DTO,DOMAIN_MODEL> handleHttpRequest(apiCall: suspend () -> Response<DTO>,mapToDomain : (DTO) -> DOMAIN_MODEL): Resource<DOMAIN_MODEL> {
    return try {
        val response = apiCall.invoke()

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(mapToDomain(it))
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
        Log.e("HttpRequest", error, throwable)
        Resource.Failed(error)
    }
}