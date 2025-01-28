package com.example.homeworktbc.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homeworktbc.authRetro.AuthService
import com.example.homeworktbc.authRetro.RetrofitObject.authService
import com.example.homeworktbc.authRetro.UserData
import kotlinx.coroutines.delay

class UserPaging(private val request : AuthService) : PagingSource<Int , UserData>() {

    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {

        val page = params.key ?: 1

        return try {
            Log.d("UserPaging", "Fetching page: $page")

            val response = request.getUsers(page)

            val rawResponse = response.raw().body?.string()
            Log.d("UserPaging", "Raw API Response: $rawResponse")


            if (response.isSuccessful){

                val data = response.body()?.data ?: emptyList()
                Log.d("UserPaging", "Fetched data: $data")

                LoadResult.Page(
                    data = data,
                    prevKey = if(page == 1) null else page -1,
                    nextKey = if (data.isEmpty()) null else page + 1
                )
            }else{
                LoadResult.Error(Exception("Error fetching data"))
            }
        }catch (e:Exception){
            Log.e("UserPaging", "Error during load: ${e.message}")
            LoadResult.Error(e)
        }
    }
}
