package com.example.homeworktbc.data.mapper

import com.example.homeworktbc.data.remote.dto.AccountDto
import com.example.homeworktbc.domain.model.Account

fun AccountDto.toDomain(): Account {
    return Account(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}