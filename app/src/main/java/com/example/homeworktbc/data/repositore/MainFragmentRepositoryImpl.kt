package com.example.homeworktbc.data.repositore

import com.example.homeworktbc.data.ApiService
import com.example.homeworktbc.data.Resource
import com.example.homeworktbc.data.ServerRepository
import com.example.homeworktbc.domain.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainFragmentRepositoryImpl @Inject constructor(private val locationApi: ApiService) :
    ServerRepository {

    override fun getUsers(): Flow<Resource<List<Location>>> {
        return handleNetworkRequest { locationApi.getItems() }
            .map { resource ->
                when (resource) {
                    is Resource.Success -> Resource.success(resource.data.map { it.toLocation() })
                    is Resource.Error -> Resource.error(resource.message, null)
                    is Resource.Loading -> Resource.loading(null)
                }
            }
    }
}

fun <T> handleNetworkRequest(call: suspend () -> T): Flow<Resource<T>> = flow {
    emit(Resource.Loading())

    try {
        val response = call()
        emit(Resource.Success(response))
    } catch (e: Exception) {
        emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
    }
}

