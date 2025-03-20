package com.example.challenge.presentation.screen.connection

import androidx.lifecycle.viewModelScope
import com.example.challenge.presentation.mapper.connection.toPresenter
import com.example.challenge.domain.core.Resource
import com.example.challenge.domain.model.connection.GetConnection
import com.example.challenge.domain.usecase.connection.GetConnectionsUseCase
import com.example.challenge.domain.usecase.datastore.ClearDataStoreUseCase
import com.example.challenge.presentation.base.BaseViewModel
import com.example.challenge.presentation.screen.connection.effect.ConnectionEffect
import com.example.challenge.presentation.screen.connection.event.ConnectionEvent
import com.example.challenge.presentation.screen.connection.state.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ConnectionsViewModel @Inject constructor(
    private val getConnectionsUseCase: GetConnectionsUseCase,
    private val clearDataStoreUseCase: ClearDataStoreUseCase
) :
    BaseViewModel<ConnectionState,ConnectionEvent,ConnectionEffect>(ConnectionState()) {

    private fun  fetchConnections(){
        viewModelScope.launch {
            getConnectionsUseCase().collect{
                when(it){
                    is Resource.Failed -> {
                        updateErrorMessage(message = it.message)
                    }
                    is Resource.Loading -> {
                        updateState { copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        updateState { copy(connections = (it.data as? List<*>).orEmpty().map { item -> (item as GetConnection).toPresenter() })
                        }
                    }
                }
            }
        }
    }

//    private fun fetchConnectionss() {
//        viewModelScope.launch {
//            getConnectionsUseCase().collect {
//                when (it) {
//                    is Resource.Loading -> _connectionState.update { currentState ->
//                        currentState.copy(
//                            isLoading = it.loading
//                        )
//                    }
//
//                    is Resource.Success -> {
//                        _connectionState.update { currentState -> currentState.copy(connections = it.data.map { it.toPresenter() }) }
//                    }
//
//                    is Resource.Failed -> updateErrorMessage(message = it.errorMessage)
//                }
//            }
//        }
//    }

    private fun logOut(){
        viewModelScope.launch {
            clearDataStoreUseCase()
            emitEffect(ConnectionEffect.NavigateToLogIn)
        }
    }

    private fun updateErrorMessage(message: String?) {
        updateState { copy(errorMessage = message) }
    }


    override fun obtainEvent(event: ConnectionEvent) {
        when(event){
            ConnectionEvent.FetchConnections -> fetchConnections()
            ConnectionEvent.LogOut -> logOut()
            ConnectionEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }
}