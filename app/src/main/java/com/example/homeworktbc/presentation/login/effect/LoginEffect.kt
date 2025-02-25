package com.example.homeworktbc.presentation.login.effect

sealed interface LoginEffect  {


    data object NavToEventsFragment : LoginEffect

    data object NavToRegisterFragment : LoginEffect

    data class ShowError(val message: String) : LoginEffect


}
