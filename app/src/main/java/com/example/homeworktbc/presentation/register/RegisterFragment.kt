package com.example.homeworktbc.presentation.register

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.data.Resource
import com.example.homeworktbc.databinding.FragmentRegisterBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun start() {
        binding.btnLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_RegisterFragment_to_logInFragment)
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val repeatedPassword = binding.etPasswordRepeat.text.toString()

            registerViewModel.validateInputsAndSignUp(email, password, repeatedPassword)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.signUpState.collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            findNavController().navigate(R.id.action_RegisterFragment_to_logInFragment)
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