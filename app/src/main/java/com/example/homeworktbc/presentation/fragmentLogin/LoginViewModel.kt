package com.example.homeworktbc.presentation.fragmentLogin

import com.example.homeworktbc.domain.repository.DataStoreRepository
import com.example.homeworktbc.domain.repository.LoginRepository
import com.example.homeworktbc.presentation.baseViewModel.BaseViewModel
import com.example.homeworktbc.presentation.fragmentLogin.effect.LoginEffect
import com.example.homeworktbc.presentation.fragmentLogin.event.LoginEvent
import com.example.homeworktbc.presentation.fragmentLogin.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<LoginState,LoginEvent,LoginEffect>(LoginState()) {


    override fun obtainEvent(event: LoginEvent) {

    }


}
