package com.example.homeworktbc.presentation.bottomSheets

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.model.CardItem
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.usecase.GetAccountsUseCase
import com.example.homeworktbc.presentation.base.BaseViewModel
import com.example.homeworktbc.presentation.bottomSheets.effect.AccountEffect
import com.example.homeworktbc.presentation.bottomSheets.event.AccountEvent
import com.example.homeworktbc.presentation.bottomSheets.state.AccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel@Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase
) : BaseViewModel<AccountState,AccountEvent,AccountEffect>(AccountState()) {

    private var isDataFetched = false

    private fun isDataLoaded(): Boolean {
        return isDataFetched
    }

    private fun getAccounts(){
        if (isDataLoaded()) return

        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            getAccountsUseCase.invoke().collect{ result ->
                when(result) {
                    is Resource.Error ->{
                        updateState { copy(isLoading = false) }
                        emitEffect(AccountEffect.ShowMessage(result.errorMessage))
                    }
                    is Resource.Loader -> updateState { copy(isLoading = true) }

                    is Resource.Success -> {
                        isDataFetched = true
                        updateState { copy(isLoading = false, isSuccess = true) }
                        emitEffect(AccountEffect.NavToMainFragment)
                        emitEffect(AccountEffect.UpdateAccounts(CardItem))

                    }
                }
            }
        }
    }





    override fun obtainEvent(event: AccountEvent) {
        when(event){
            AccountEvent.ItemClicked -> viewModelScope.launch {emitEffect(AccountEffect.NavToMainFragment)}
            AccountEvent.FetchAccounts -> getAccounts()
        }
    }

}