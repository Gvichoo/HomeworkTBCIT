package com.example.homeworktbc.presentation.bottomSheets.event

sealed class AccountEvent {
    data object ItemClicked : AccountEvent()
    data object FetchAccounts : AccountEvent()
}