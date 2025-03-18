package com.example.homeworktbc.domain.usecase

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.model.Category
import com.example.homeworktbc.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<Category>>> {
        return categoryRepository.getCategories().map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val filteredCategories = if (query.isNotEmpty()) {
                        filterCategoriesByQuery(resource.data ?: emptyList(), query.lowercase())
                    } else {
                        resource.data ?: emptyList()
                    }
                    Resource.Success(filteredCategories)
                }
                is Resource.Failed -> Resource.Failed(resource.message.orEmpty())
                is Resource.Loading -> Resource.Loading()
            }
        }
    }



    private fun List<Category>.flattenCategories(): List<Category> {
        val result = mutableListOf<Category>()
        for (category in this) {
            addCategoryToList(category, result)
        }
        return result
    }

    private fun filterCategoriesByQuery(
        categories: List<Category>,
        query: String,
    ): List<Category> {
        return categories.flattenCategories().filter { it.name.lowercase().startsWith(query) }
    }

    private fun addCategoryToList(category: Category, result: MutableList<Category>) {
        result.add(category)
        category.children.forEach { child ->
            addCategoryToList(child, result)
        }
    }

}