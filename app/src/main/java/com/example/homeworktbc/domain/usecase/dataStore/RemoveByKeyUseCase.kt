package com.example.homeworktbc.domain.usecase.dataStore

import com.example.homeworktbc.domain.repository.DataStoreRepository
import javax.inject.Inject

class RemoveByKeyUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke() {
        return dataStoreRepository.removeByKey()
    }
}