package com.example.homeworktbc.data.repository

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
        return handleHttpRequest(
            apiCall = { serviceApi.getCategories() },
            mapToDomain = { dtoList -> dtoList.map { it.toDomain() } }
        )
    }
}