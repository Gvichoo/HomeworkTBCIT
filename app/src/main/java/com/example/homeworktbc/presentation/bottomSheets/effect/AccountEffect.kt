package com.example.homeworktbc.presentation.bottomSheets.effect

import com.example.homeworktbc.domain.model.Account


sealed class AccountEffect {
    data class ShowMessage(val message: String) : AccountEffect()
    data object NavToMainFragment : AccountEffect()
    data class UpdateAccounts(val accounts: List<Account>) : AccountEffect()
}