package com.example.homeworktbc.domain.repository

import androidx.paging.PagingData
import com.example.homeworktbc.data.entity.User
import kotlinx.coroutines.flow.Flow


interface HomeRepository {
    fun getUsersPager(): Flow<PagingData<User>>
}