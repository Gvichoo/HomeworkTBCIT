package com.example.homeworktbc.presentation.fragmentRegister

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.remote.request.AuthRequest
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.core.ValidationResult
import com.example.homeworktbc.domain.repository.RegisterRepository
import com.example.homeworktbc.domain.usecase.register.RegisterUseCase
import com.example.homeworktbc.domain.usecase.validation.EmailValidationUseCase
import com.example.homeworktbc.domain.usecase.validation.PasswordValidationUseCase
import com.example.homeworktbc.domain.usecase.validation.RepeatedPasswordValidationUseCase
import com.example.homeworktbc.presentation.baseViewModel.BaseViewModel
import com.example.homeworktbc.presentation.fragmentLogin.effect.LoginEffect
import com.example.homeworktbc.presentation.fragmentRegister.effect.RegisterEffect
import com.example.homeworktbc.presentation.fragmentRegister.event.RegisterEvent
import com.example.homeworktbc.presentation.fragmentRegister.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseRepositoryCase: RegisterUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
    private val repeatedPasswordValidationUseCase: RepeatedPasswordValidationUseCase
) : BaseViewModel<RegisterState, RegisterEvent, RegisterEffect>(RegisterState()) {



    private fun validateInputsAndRegister(email: String, password: String, passwordRepeated: String){
        val emailValidation = emailValidationUseCase(email)
        val passwordValidation = passwordValidationUseCase(password)
        val repeatedPasswordValidation = repeatedPasswordValidationUseCase(password,passwordRepeated)
        when{
            emailValidation is ValidationResult.Failure ->{
                showError(emailValidation.error.message)
                return
            }
            passwordValidation is ValidationResult.Failure ->{
                showError(passwordValidation.error.message)
                return
            }
            repeatedPasswordValidation is ValidationResult.Failure -> {
                showError(repeatedPasswordValidation.error.message)
            }
            else -> {
                registerUser(email,password,)
            }
        }
    }

    private fun showError(errorMessage: String) {
        viewModelScope.launch {
            emitEffect(RegisterEffect.ShowError(errorMessage))
        }
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
                    updateState { copy(isSuccess = true,isLoading = true) }
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

