package com.example.homeworktbc.domain.usecase.home

import androidx.paging.PagingData
import com.example.homeworktbc.data.local.entity.User
import com.example.homeworktbc.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersPagerUseCase @Inject constructor( private val homeRepository: HomeRepository) {
    operator fun invoke() : Flow<PagingData<User>> {
        return homeRepository.getUsersPager()
    }
}