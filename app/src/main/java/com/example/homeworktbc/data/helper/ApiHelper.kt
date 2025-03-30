package com.example.homeworktbc.data.helper

import com.example.homeworktbc.domain.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ApiHelper {
    suspend fun <DTO, DOMAIN_MODEL> handleHttpRequest(
        apiCall: suspend () -> Response<DTO>,
        mapToDomain: (DTO) -> DOMAIN_MODEL
    ): Flow<Resource<DOMAIN_MODEL>> = flow {
        emit(Resource.Loader(loading = true))
        try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(data = mapToDomain(it)))
                } ?: emit(Resource.Error(errorMessage = "Something is wrong"))
            } else {
                val errorMessage = response.message().ifEmpty { "Error code: ${response.code()}" }
                emit(Resource.Error(errorMessage = errorMessage))
            }
            emit(Resource.Loader(loading = false))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> emit(Resource.Error(errorMessage = throwable.message ?: "IO Error"))
                is HttpException -> emit(Resource.Error(errorMessage = throwable.message ?: "HTTP Error"))
                is IllegalStateException -> emit(Resource.Error(errorMessage = throwable.message ?: "IllegalState Error"))
                else -> emit(Resource.Error(errorMessage = throwable.message ?: "Unknown Error"))
            }
            emit(Resource.Loader(loading = false))
        }
    }
}
