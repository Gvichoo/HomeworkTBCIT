package com.example.homeworktbc.domain.mapper

import com.example.homeworktbc.domain.model.Account
import com.example.homeworktbc.presentation.model.AccountPresentation

fun Account.toPresentation(): AccountPresentation {
    return AccountPresentation(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}