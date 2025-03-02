package com.example.homeworktbc.domain.usecase.removeKey

import androidx.datastore.preferences.core.Preferences
import com.example.homeworktbc.domain.repository.DataStoreRepository
import javax.inject.Inject

class RemoveKeyUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(key: Preferences.Key<String>){
        dataStoreRepository.removeByKey(key)
    }
}