package com.example.homeworktbc.presentation.model

data class AccountPresentation(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String?
)