package com.example.homeworktbc.fragmentLogin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentLogInBinding
import com.example.homeworktbc.fragmentRegister.Result

class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
    private lateinit var viewModel: LoginViewModel

    override fun start() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setFragmentResultListener("registration_request_key") { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")
            binding.etLogin.setText(email)
            binding.etPassword.setText(password)
        }

        navToRegister()

        binding.btnLogin.setOnClickListener {
            val email = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.loginUser(email, password) { result ->
                when (result) {
                    is Result.Failed -> {
                        Toast.makeText(requireContext(),
                            getString(result.error.errorMessageResource), Toast.LENGTH_SHORT).show()
                    }
                    is Result.IsLoading -> {
                        binding.loader.visibility = if (result.isLoading) View.VISIBLE else View.GONE
                    }
                    is Result.Success -> {
                        setFragmentResult(
                            "login_success_key",
                            Bundle().apply {
                                putString("email", email)
                            }
                        )
                        Toast.makeText(requireContext(), result.result, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                    }
                }
            }
        }
    }

    private fun navToRegister() {
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }
    }
}
