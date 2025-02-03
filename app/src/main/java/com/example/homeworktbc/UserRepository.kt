package com.example.homeworktbc

import com.example.homeworktbc.retrofit.ApiResponse
import com.example.homeworktbc.retrofit.RetrofitClient
import com.example.homeworktbc.roomClasses.User
import com.example.homeworktbc.roomClasses.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class UserRepository(private val userDao: UserDao) {

    suspend fun fetchUsersFromServer(): Flow<Response<ApiResponse>> = flow {
        try {
            val response = RetrofitClient.retrofit.fetchUsers()
            emit(response)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null && apiResponse.status) {
                    val users = apiResponse.users.map {
                        User(
                            id = it.id,
                            firstName = it.first_name,
                            lastName = it.last_name,
                            profileImage = it.avatar,
                            about = it.about,
                            activation_status = it.activation_status
                        )
                    }
                    userDao.insertUsers(users)
                }
            } else {
                throw Exception("API Request failed: ${response.message()}")
            }
        } catch (e: Exception) {
            throw Exception("Error fetching data from server: ${e.message}")
        }
    }

    fun getUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }
}