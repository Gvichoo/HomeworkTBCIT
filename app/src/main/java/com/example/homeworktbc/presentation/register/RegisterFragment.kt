package com.example.homeworktbc.presentation.register

import android.text.InputType
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.databinding.FragmentRegisterBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun start() {

        singIn()

        signUp()

        observeViewModel()

        makePasswordVisible()

    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.signUpState.collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_RegisterFragment_to_logInFragment)
                        }
                        is Resource.Failed -> {
                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
                        }
                        is Resource.Loading -> {
                        }
                    }
                }
            }
        }
    }

    private fun signUp(){
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val repeatedPassword = binding.etPasswordRepeat.text.toString().trim()

            registerViewModel.validateInputsAndSignUp(email, password, repeatedPassword)
        }
    }

    private fun singIn(){
        binding.btnLogIn.setOnClickListener {
            navToLoginFragment()
        }
    }

    private fun navToLoginFragment(){
        findNavController().navigate(R.id.action_RegisterFragment_to_logInFragment)
    }
    private fun makePasswordVisible(){
        var isPasswordVisible = false

        binding.btnSeen.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            val inputType = if (isPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            binding.etPassword.inputType = inputType
            binding.etPasswordRepeat.inputType = inputType

            binding.btnSeen.setImageResource(
                if (isPasswordVisible) R.drawable.seen else R.drawable.unseen
            )

            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordRepeat.setSelection(binding.etPasswordRepeat.text.length)
        }
    }

}
