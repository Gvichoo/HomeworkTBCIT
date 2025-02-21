package com.example.homeworktbc.presentation.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.databinding.FragmentLogInBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val loginViewModel: LogInViewModel by viewModels()

    override fun start() {
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_RegisterFragment)
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginViewModel.validateInputsAndLogin(email, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginState.collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            findNavController().navigate(R.id.action_logInFragment_to_splashFragment)
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
                        }
                        is Resource.Loading -> {
                        }
                    }
                }
            }
        }
    }
}