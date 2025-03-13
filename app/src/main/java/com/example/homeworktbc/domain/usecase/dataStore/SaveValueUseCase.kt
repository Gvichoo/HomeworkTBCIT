package com.example.homeworktbc.domain.usecase.dataStore

import com.example.homeworktbc.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveValueUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(email : String){
        return dataStoreRepository.saveValue(email)
    }
}