package com.example.homeworktbc.presentation.login

import android.text.InputType
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentLogInBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.login.effect.LoginEffect
import com.example.homeworktbc.presentation.login.event.LoginEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val loginViewModel: LogInViewModel by viewModels()

    override fun start() {
        startSignUpClickListener()

        startSignInClickListener()

        makePasswordVisible()


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.effects.collect { effect ->
                    when (effect) {

                        LoginEffect.NavToEventsFragment ->
                            navToEventFragment()

                        LoginEffect.NavToRegisterFragment ->
                            findNavController().navigate(R.id.action_logInFragment_to_RegisterFragment)

                        is LoginEffect.ShowError -> {
                            showError(effect.message)
                        }
                    }
                }
            }
        }
    }

    private fun navToEventFragment(){
        findNavController().navigate(R.id.action_logInFragment_to_eventsFragment)
    }

    private fun startSignUpClickListener(){
        binding.btnSignUp.setOnClickListener {
            loginViewModel.obtainEvent(LoginEvent.SignUpClicked)
        }
    }

    private fun startSignInClickListener(){
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginViewModel.obtainEvent(LoginEvent.LoginButtonClicked(email, password))
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

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}