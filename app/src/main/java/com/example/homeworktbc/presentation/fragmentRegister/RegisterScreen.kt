//package com.example.homeworktbc.presentation.fragmentRegister
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.homeworktbc.R
//import com.example.homeworktbc.presentation.components.AuthTextField
//import com.example.homeworktbc.presentation.fragmentRegister.effect.RegisterEffect
//import com.example.homeworktbc.presentation.fragmentRegister.event.RegisterEvent
//import com.example.homeworktbc.presentation.fragmentRegister.state.RegisterState
//import com.example.homeworktbc.presentation.theme.dimens.Dimens
//import kotlinx.coroutines.flow.collect
//
//
//@Composable
//fun RegisterScreen(
//    viewModel: RegisterViewModel = hiltViewModel(),
//    navigateToLogin: (email: String, password: String) -> Unit
//) {
//    val context = LocalContext.current
//
//    CollectSideEffect(flow = viewModel.effects) { effect ->
//        when (effect) {
//
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        RegisterContent(
//            state = viewModel.viewState,
//            uiState = viewModel.effects,
//            onEvent = { registerEvent ->
//                viewModel.obtainEvent(registerEvent)
//            }
//        )
//
//        // Handle Loader State
//        if (viewModel.viewState.loader) {
//            MyCircularProgress()
//        }
//    }
//}
//
//@Composable
//fun RegisterContent(
//    state: RegisterState,
//    uiState: RegisterEffect,
//    onEvent: (RegisterEvent) -> Unit
//) {
//    val scrollState = rememberScrollState()
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 30.dp)
//            .verticalScroll(scrollState),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = Modifier.height(Dimens.SpacingXXL))
//
//        Text(
//            text = "Register",
//            style = MaterialTheme.typography.titleLarge,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth(),
//        )
//
//        Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
//
//        // Image
//        Image(
//            painter = painterResource(id = R.drawable.iconeye),
//            contentDescription = "Register Illustration",
//            modifier = Modifier
//                .align(alignment = Alignment.CenterHorizontally)
//                .fillMaxWidth(0.6f)
//                .aspectRatio(1.2f),
//        )
//
//        Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
//
//        // Form
//        Column(
//            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingLarge),
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            // Email
//            AuthTextField(
//                value = uiState.email,
//                onValueChange = { onEvent(RegisterEvent.EmailChanged(it)) },
//                label = "Email",
//                keyboardType = KeyboardType.Email,
//                leadingIcon = Icons.Default.Email,
//                errorResource = state.emailErrorResource
//            )
//
//            // Password
//            AuthTextField(
//                value = uiState.password,
//                onValueChange = {
//                    onEvent(
//                        RegisterEvent.PasswordChanged(
//                            password = it,
//                            repeatedPassword = uiState.repeatedPassword
//                        )
//                    )
//                },
//                label = "Password",
//                keyboardType = KeyboardType.Password,
//                leadingIcon = Icons.Default.Lock,
//                errorResource = state.passwordErrorResource,
//                isPassword = true,
//                isPasswordVisible = uiState.passwordVisible,
//                onTrailingIconClick = { onEvent(RegisterEvent.TogglePasswordVisibility) }
//            )
//
//            // Repeat Password
//            AuthTextField(
//                value = uiState.repeatedPassword,
//                onValueChange = {
//                    onEvent(
//                        RegisterEvent.RepeatedPasswordChanged(
//                            repeatedPassword = it,
//                            password = uiState.password,
//                        )
//                    )
//                },
//                label = "Repeat Password",
//                keyboardType = KeyboardType.Password,
//                leadingIcon = Icons.Default.Lock,
//                errorResource = state.repeatedPasswordErrorResource,
//                isPassword = true,
//                isPasswordVisible = uiState.repeatedPasswordVisible,
//                onTrailingIconClick = { onEvent(RegisterEvent.ToggleRepeatPasswordVisibility) }
//            )
//
//            // Button
//            Button(
//                onClick = {
//                    onEvent(
//                        RegisterEvent.Submit
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(height = Dimens.ButtonHeight),
//                enabled = state.isSignUpBtnEnabled
//            ) {
//                Text(
//                    stringResource(R.string.submit),
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//
//            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//    User {
//        Box(modifier = Modifier.fillMaxSize()) {
//            RegisterContent(
//                state = RegisterState(),
//                uiState = RegisterUiState(),
//                onEvent = {}
//            )
//            if (false) {
//                MyCircularProgress()
//            }
//        }
//    }
//}