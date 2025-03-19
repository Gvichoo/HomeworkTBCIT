package com.example.homeworktbc.data.repository

import android.util.Log
import com.example.homeworktbc.data.mapper.toDomain
import com.example.homeworktbc.data.remote.api.CategoryApiService
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.core.handleHttpRequest
import com.example.homeworktbc.domain.model.Category
import com.example.homeworktbc.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val serviceApi : CategoryApiService
) : CategoryRepository {
    override suspend fun getCategories(): Flow<Resource<List<Category>>> {

        val apiResponse = serviceApi.getCategories()
        Log.d("API Response", apiResponse.toString())
        return handleHttpRequest(
            apiCall = { serviceApi.getCategories() },
            mapToDomain = { dtoList -> dtoList.map {
                Log.d("Category DTO", it.toString())
                it.toDomain() } }
        )
    }
}