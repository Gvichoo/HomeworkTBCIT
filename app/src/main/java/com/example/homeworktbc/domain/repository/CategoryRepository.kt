package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories() : Flow<Resource<List<Category>>>
}