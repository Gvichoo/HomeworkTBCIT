package com.example.homeworktbc.domain.usecase.saveEmail

import androidx.datastore.preferences.core.Preferences
import com.example.homeworktbc.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveValueUseCase @Inject constructor( private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(key: Preferences.Key<String>,value :String){
        return dataStoreRepository.saveValue(key,value)
    }
}