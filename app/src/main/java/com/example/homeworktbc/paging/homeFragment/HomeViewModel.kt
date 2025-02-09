package com.example.homeworktbc.paging.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homeworktbc.paging.retro.PagerRetrofit
import com.example.homeworktbc.paging.retro.ServicePage
import com.example.homeworktbc.paging.retro.UserData
import com.example.homeworktbc.paging.UserPaging
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel(){
    private val authService: ServicePage = PagerRetrofit.retrofit


    private fun getUserPagingFlow(): Flow<PagingData<UserData>> {
        return Pager(
            config = PagingConfig(pageSize = 6, prefetchDistance = 1),
            pagingSourceFactory = { UserPaging(authService) }
        ).flow.cachedIn(viewModelScope)
    }

    val users: Flow<PagingData<UserData>> = getUserPagingFlow()


}