package com.example.homeworktbc.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.homeworktbc.paging.data.database.RoomDb
import com.example.homeworktbc.paging.data.entity.User
import com.example.homeworktbc.paging.data.retro.UserService
import kotlinx.coroutines.flow.Flow

class Repository(
    private val database: RoomDb,
    private val userService: UserService
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