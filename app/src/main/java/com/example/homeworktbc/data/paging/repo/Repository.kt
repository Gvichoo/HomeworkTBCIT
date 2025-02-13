package com.example.homeworktbc.data.paging.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.homeworktbc.paging.UserRemoteMediator
import com.example.homeworktbc.data.paging.database.RoomDb
import com.example.homeworktbc.data.paging.entity.User
import com.example.homeworktbc.data.remote.api.UserApi
import kotlinx.coroutines.flow.Flow

open class Repository(
    private val database: RoomDb,
    private val userService: UserApi
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getUsersPager(): Flow<PagingData<User>> {
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