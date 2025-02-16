package com.example.homeworktbc.presentation.fragmentLogin

import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.data.resource.Results
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun start() {
        setupPasswordToggle(binding.etPassword, binding.ivEye)


        setFragmentResultListener("registration_request_key") { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")
            binding.etLogin.setText(email)
            binding.etPassword.setText(password)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val rememberMe = binding.cbRememberMe.isChecked

            viewModel.loginUser(email, password, rememberMe)
        }

        navToRegister()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginState.collect { result ->
                    when (result) {
                        is Results.Loading -> {
                            binding.loader.visibility = View.VISIBLE
                        }
                        is Results.Success -> {
                            binding.loader.visibility = View.GONE
                            Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                            val email = binding.etLogin.text.toString().trim()
                            // Send email to ProfileFragment
                            setFragmentResult("login_success_key", bundleOf("email" to email))
                            findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                        }
                        is Results.Failed -> {
                            binding.loader.visibility = View.GONE
                            Toast.makeText(requireContext(), result.error.message, Toast.LENGTH_SHORT).show()
                        }

                        null -> {

                        }
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

    private fun setupPasswordToggle(editText: AppCompatEditText, toggleButton: AppCompatImageButton) {
        toggleButton.setOnClickListener {
            if (editText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                toggleButton.setImageResource(R.drawable.iconeye)
            } else {
                editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                toggleButton.setImageResource(R.drawable.iconeye)
            }
            editText.setSelection(editText.text?.length ?: 0)
        }
    }
}