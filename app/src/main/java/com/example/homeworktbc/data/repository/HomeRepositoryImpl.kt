package com.example.homeworktbc.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.homeworktbc.data.database.RoomDb
import com.example.homeworktbc.data.entity.User
import com.example.homeworktbc.data.remote.api.UserApi
import com.example.homeworktbc.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val database: RoomDb,
    private val userService: UserApi
) : HomeRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getUsersPager(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                prefetchDistance = 1
            ),
            remoteMediator = UserRemoteMediator(userService, database),
            pagingSourceFactory = { database.userDao().pagingSource() }
        ).flow
    }
}