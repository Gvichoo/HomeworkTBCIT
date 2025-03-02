package com.example.homeworktbc.domain.usecase.session

import androidx.datastore.preferences.core.Preferences
import com.example.homeworktbc.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSessionValueUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    //Invoke = operator fun
    operator fun invoke(key: Preferences.Key<String>): Flow<String> {
        return dataStoreRepository.readValue(key)
    }
}