package com.example.homeworktbc.presentation.register

import android.text.InputType
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentRegisterBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.register.effect.RegisterEffect
import com.example.homeworktbc.presentation.register.event.RegisterEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun start() {

        startSingInClickListener()

        startSignUpClickListener()

        observeViewModel()

        makePasswordVisible()

    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.effects.collect { effect ->
                    when (effect) {
                        RegisterEffect.NavToLogInFragment -> {
                            navToLoginFragment()
                        }
                        is RegisterEffect.ShowError -> {
                            showError(effect.message)
                        }
                    }
                }
            }
        }
    }

    private fun startSignUpClickListener(){
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val repeatedPassword = binding.etPasswordRepeat.text.toString().trim()

            registerViewModel.obtainEvent(RegisterEvent.SignUpButtonClicked(email, password, repeatedPassword))
        }
    }

    private fun startSingInClickListener(){
        binding.btnLogIn.setOnClickListener {
            registerViewModel.obtainEvent(RegisterEvent.LogInClicked)
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

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}
