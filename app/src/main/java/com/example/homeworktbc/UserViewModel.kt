package com.example.homeworktbc

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.roomClasses.AppDatabase
import com.example.homeworktbc.roomClasses.User
import com.example.homeworktbc.roomClasses.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> get() = _error

    fun init(context: Context) {
        userDao = AppDatabase.getDatabase(context).userDao()
        userRepository = UserRepository(userDao)
    }

    fun fetchUsers(context: Context) {
        if (!isOnline(context)) return
        _loading.value = true
        _error.value = false
        viewModelScope.launch {
            try {
                userRepository.fetchUsersFromServer().collect { response ->
                    if (response.isSuccessful) {
                        val usersList = response.body()?.users?.map {
                            User(
                                id = it.id,
                                firstName = it.first_name,
                                lastName = it.last_name,
                                profileImage = it.avatar,
                                about = it.about,
                                activation_status = it.activation_status
                            )
                        }
                        _users.value = usersList ?: emptyList()
                    } else {
                        _error.value = true
                    }
                }
            } catch (e: Exception) {
                _error.value = true
            } finally {
                _loading.value = false
            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}