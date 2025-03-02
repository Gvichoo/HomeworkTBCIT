package com.example.homeworktbc.domain.usecase.saveLanguage

import com.example.homeworktbc.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(language : String){
        dataStoreRepository.saveLanguage(language)
    }
}