package com.example.homeworktbc.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homeworktbc.fragmentRegister.Result
import com.example.homeworktbc.fragmentRegister.handleHttpRequest
import com.example.homeworktbc.paging.retro.ServicePage
import com.example.homeworktbc.paging.retro.UserData

class UserPaging(private val request : ServicePage) : PagingSource<Int, UserData>() {

    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        val page = params.key ?: 1


        return try {
            val result =handleHttpRequest { request.getUsers(page) }

            when (result) {
                is Result.Success -> {
                    val data = result.result.data
                    LoadResult.Page(
                        data = data,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1
                    )
                }
                is Result.Failed -> {
                    LoadResult.Error(Exception("Error fetching data"))
                }

                else -> LoadResult.Error(Exception("Unknown error"))

            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}