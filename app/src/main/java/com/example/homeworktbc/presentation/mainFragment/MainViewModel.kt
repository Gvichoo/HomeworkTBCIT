package com.example.homeworktbc.presentation.mainFragment

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.repository.CategoryRepository
import com.example.homeworktbc.domain.usecase.FilterCategoriesUseCase
import com.example.homeworktbc.presentation.baseViewModel.BaseViewModel
import com.example.homeworktbc.presentation.mainFragment.effect.MainEffect
import com.example.homeworktbc.presentation.mainFragment.event.MainEvent
import com.example.homeworktbc.presentation.mainFragment.state.MainState
import com.example.homeworktbc.presentation.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val filterCategoriesUseCase: FilterCategoriesUseCase
) : BaseViewModel<MainState, MainEvent, MainEffect>(MainState()) {

    private var debounceJob: Job? = null
    private val debounceDelayMillis = 500L


    private fun getCategories() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            categoryRepository.getCategories().collect { result ->
                when (result) {
                    is Resource.Failed -> {
                        emitEffect(MainEffect.ShowMessage(result.message ?: "Failed!"))
                        updateState { copy(isLoading = false) }
                    }

                    is Resource.Loading ->
                        updateState { copy(isLoading = true) }

                    is Resource.Success -> {
                        val categoriesForUI =
                            result.data?.map { it.toPresentation() } ?: emptyList()
                        updateState { copy(isLoading = false, categories = categoriesForUI) }
                    }
                }

            }
        }
    }

    private fun filterCategories(query: String) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            filterCategoriesUseCase(query).collect { result ->
                when (result) {
                    is Resource.Failed -> {
                        emitEffect(MainEffect.ShowMessage(result.message ?: "Filtering failed"))
                        updateState { copy(isLoading = false) }
                    }

                    is Resource.Loading -> {
                        updateState { copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        val filteredCategories = result.data?.map { it.toPresentation() } ?: emptyList()
                        updateState { copy(categories = filteredCategories) }
                        updateState { copy(isLoading = false) }
                    }
                }
            }
        }
    }

    private fun onSearchQueryChanged(query: String) {
        debounceJob?.cancel()

        debounceJob = viewModelScope.launch {
            delay(debounceDelayMillis)
            filterCategories(query)
        }
    }


    override fun obtainEvent(event: MainEvent) {
        when (event) {
            MainEvent.FetchCategories -> getCategories()
            is MainEvent.FilterCategories -> onSearchQueryChanged(event.query)
        }
    }
}