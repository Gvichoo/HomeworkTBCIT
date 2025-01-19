package com.example.homeworktbc.fragmentLogin

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentLogInBinding
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun start() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.loginUser(email, password)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                loginViewModel.loginResult.collect { success ->
                    if (success != null) {
                        if (success) {
                            Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.welcomeFragment)
                        } else {
                            Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
