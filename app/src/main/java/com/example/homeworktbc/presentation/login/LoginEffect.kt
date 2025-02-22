package com.example.homeworktbc.presentation.login

sealed interface LoginEffect  {


    data object NavToEventsFragment : LoginEffect

    data object NavToRegisterFragment : LoginEffect



}
