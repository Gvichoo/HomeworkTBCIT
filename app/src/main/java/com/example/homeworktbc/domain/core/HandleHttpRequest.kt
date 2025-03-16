package com.example.homeworktbc.domain.core

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <DTO, DOMAIN_MODEL> handleHttpRequest(apiCall: suspend () -> Response<DTO>, mapToDomain: (DTO) -> DOMAIN_MODEL
): Flow<Resource<DOMAIN_MODEL>> = flow {
    try {
        emit(Resource.Loading())

        val response = apiCall.invoke()

        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(mapToDomain(it)))
            } ?: emit(Resource.Failed("No data received."))
        } else {
            emit(Resource.Failed("Error: ${response.message()}"))
        }

    } catch (throwable: Throwable) {
        val error = when (throwable) {
            is IOException -> "Network error. Please check your connection."
            is HttpException -> "Server error: ${throwable.code()} - ${throwable.message()}"
            is IllegalStateException -> "Unexpected response format."
            else -> "Unknown error: ${throwable.message}"
        }
        Log.e("API_ERROR", error, throwable)
        emit(Resource.Failed(error))
    }
}
