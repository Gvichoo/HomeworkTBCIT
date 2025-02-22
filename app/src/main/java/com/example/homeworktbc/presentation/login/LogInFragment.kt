package com.example.homeworktbc.presentation.login

import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentLogInBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val loginViewModel: LogInViewModel by viewModels()

    override fun start() {
        signUp()

        signIn()

        makePasswordVisible()

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                loginViewModel.loginState.collect { resource ->
//                    when (resource) {
//                        is Resource.Success -> {
//                            navToEventFragment()
//
//                        }
//                        is Resource.Failed -> {
//                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
//                        }
//                        is Resource.Loading -> {
//                        }
//                    }
//                }
//            }
//        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginEffectFlow.collect { effect ->
                    when (effect) {

                        LoginEffect.NavToEventsFragment ->
                            navToEventFragment()

                        LoginEffect.NavToRegisterFragment -> Unit
                    }
                }
            }
        }

    }

    private fun navToEventFragment(){
        findNavController().navigate(R.id.action_logInFragment_to_eventsFragment)
    }

    private fun signUp(){
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_RegisterFragment)
        }
    }

    private fun signIn(){
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginViewModel.validateInputsAndLogin(email, password)
        }
    }

    private fun makePasswordVisible(){
        var isPasswordVisible = false

        binding.btnSeen.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.btnSeen.setImageResource(R.drawable.seen)
            } else {
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.btnSeen.setImageResource(R.drawable.unseen)
            }

            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

}