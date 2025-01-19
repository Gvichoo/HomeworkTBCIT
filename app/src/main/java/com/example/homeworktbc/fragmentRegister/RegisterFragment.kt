package com.example.homeworktbc.fragmentRegister

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun start() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etLoginRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                registerViewModel.registerUser(email, password)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                registerViewModel.registerResult.collect { success ->
                    if (success != null) {
                        if (success) {
                            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.logInFragment)
                        } else {
                            Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
