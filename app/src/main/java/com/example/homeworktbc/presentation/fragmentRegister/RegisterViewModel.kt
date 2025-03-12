package com.example.homeworktbc.presentation.fragmentRegister

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.repository.RegisterRepository
import com.example.homeworktbc.domain.usecase.register.RegisterUseCase
import com.example.homeworktbc.presentation.baseViewModel.BaseViewModel
import com.example.homeworktbc.presentation.fragmentRegister.effect.RegisterEffect
import com.example.homeworktbc.presentation.fragmentRegister.event.RegisterEvent
import com.example.homeworktbc.presentation.fragmentRegister.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseRepositoryCase: RegisterUseCase
) : BaseViewModel<RegisterState, RegisterEvent, RegisterEffect>(RegisterState()) {



    private fun validateInputsAndRegister(email: String, password: String, passwordRepeated: String){
        if(validateInputs(email, password, passwordRepeated)){
            registerUser(email, password)
        }
    }


    private fun validateInputs(email: String, password: String, passwordRepeated: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError("Empty fields!"))
            }
            return false
        }

        if (password.length < 8) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError("Password should be at least 8 characters!"))
            }
            return false
        }

        if (password != passwordRepeated) {
            viewModelScope.launch {
                emitEffect(RegisterEffect.ShowError("Passwords doesn't match!"))
            }
            return false
        }
        return true
    }


    private fun registerUser(email: String, password: String) {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            when(val result = registerUseRepositoryCase.invoke(AuthRequest(email, password))){
                is Resource.Failed -> {
                    emitEffect(RegisterEffect.ShowError(result.message.toString())
                    )
                    updateState { copy(isLoading = true) }
                }
                is Resource.Loading ->
                    updateState { copy(isLoading = true) }

                is Resource.Success -> {
                    updateState { copy(isSuccess = true) }
                    updateState { copy(isLoading = true) }
                    emitEffect(RegisterEffect.NavToLogInFragment)
                }
            }
        }
    }




    override fun obtainEvent(event: RegisterEvent) {
        when(event){
            is RegisterEvent.SignUpButtonClicked -> validateInputsAndRegister(event.email,event.password,event.repeatedPassword)
        }
    }

}

