package com.example.homeworktbc.domain.usecase.datastore

import androidx.datastore.preferences.core.Preferences
import com.example.homeworktbc.domain.repository.DataStoreRepository
import javax.inject.Inject

class RemoveByKeyUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(key: Preferences.Key<String>) {
        return dataStoreRepository.removeByKey(key)
    }
}