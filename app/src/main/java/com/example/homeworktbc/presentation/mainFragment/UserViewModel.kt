package com.example.homeworktbc.presentation.mainFragment

import androidx.lifecycle.ViewModel
import com.example.homeworktbc.domain.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @AssistedInject constructor(
    private var userRepository: UserRepository,
    @Assisted private val userId: String
) : ViewModel() {

    fun getUserName(): String {
        return userRepository.getUser()
    }
}
