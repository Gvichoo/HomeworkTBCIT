package com.example.homeworktbc.fragmentPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homeworktbc.authRetro.AuthService
import com.example.homeworktbc.authRetro.RetrofitObject
import com.example.homeworktbc.authRetro.UserData
import com.example.homeworktbc.paging.UserPaging
import kotlinx.coroutines.flow.Flow

class PageViewModel : ViewModel(){
    private val authService: AuthService = RetrofitObject.authService

    private fun getUserPagingFlow(): Flow<PagingData<UserData>> {
        return Pager(
            config = PagingConfig(pageSize = 6, prefetchDistance = 1),
            pagingSourceFactory = { UserPaging(authService) }
        ).flow.cachedIn(viewModelScope)
    }

    val users: Flow<PagingData<UserData>> = getUserPagingFlow()
}