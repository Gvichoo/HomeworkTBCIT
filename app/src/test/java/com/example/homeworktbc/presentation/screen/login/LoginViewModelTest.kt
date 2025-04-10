package com.example.homeworktbc.presentation.screen.login

import com.example.homeworktbc.domain.usecase.validation.EmailValidationUseCase
import com.example.homeworktbc.domain.usecase.validation.PasswordValidationUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before

class LoginViewModelTest{

    private val testDispatcher = StandardTestDispatcher()

    @MockK lateinit var mockLoginUseCase: LoginUseCase
    @MockK lateinit var mockEmailValidationUseCase: EmailValidationUseCase
    @MockK lateinit var mockPasswordValidationUseCase: PasswordValidationUseCase

    private lateinit var viewModel: LoginViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        viewModel = LoginViewModel(
            mockLoginUseCase,
            mockEmailValidationUseCase,
            mockPasswordValidationUseCase
        )
    }



}