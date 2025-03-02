package com.example.homeworktbc.domain.usecase.readLanguage

import com.example.homeworktbc.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadLanguageUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    operator fun invoke(): Flow<String> {
        return dataStoreRepository.readLanguage()
    }
}