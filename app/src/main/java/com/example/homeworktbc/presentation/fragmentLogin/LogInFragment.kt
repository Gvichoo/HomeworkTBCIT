package com.example.homeworktbc.presentation.fragmentLogin

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.data.datastore.DataStoreManager
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentLogInBinding
import com.example.homeworktbc.presentation.fragmentRegister.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    // Using the viewModels delegate to get the LoginViewModel injected by Hilt
    private val viewModel: LoginViewModel by viewModels()

    override fun start() {
        setupPasswordToggle(binding.etPassword, binding.ivEye)

        // Reading saved email from DataStore
        viewLifecycleOwner.lifecycleScope.launch {
            DataStoreManager.readValue<Any>(PreferenceKeys.email)?.collect { savedEmail ->
                Log.d("DataStore", "Read value: $savedEmail")
            }
        }

        // Set up fragment result listener for when user comes back from the Register screen
        setFragmentResultListener("registration_request_key") { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")
            binding.etLogin.setText(email)
            binding.etPassword.setText(password)
        }

        // Navigating to Register screen
        navToRegister()

        // Handling Login button click
        binding.btnLogin.setOnClickListener {
            val email = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val rememberMe = binding.cbRememberMe.isChecked

            // Call ViewModel's loginUser method
            viewModel.loginUser(email, password, rememberMe) { result ->
                when (result) {
                    is Result.Failed -> {
                        Toast.makeText(requireContext(), getString(result.error.errorMessageResource), Toast.LENGTH_SHORT).show()
                    }
                    is Result.IsLoading -> {
                        binding.loader.visibility = if (result.isLoading) View.VISIBLE else View.GONE
                    }
                    is Result.Success -> {
                        if (rememberMe) {
                            // Save email to DataStore if "Remember Me" is checked
                            viewLifecycleOwner.lifecycleScope.launch {
                                DataStoreManager.saveValue(PreferenceKeys.email, email)
                            }
                        }

                        // Sending successful login result to another fragment
                        setFragmentResult(
                            "login_success_key",
                            Bundle().apply {
                                putString("email", email)
                            }
                        )
                        Toast.makeText(requireContext(), result.result, Toast.LENGTH_SHORT).show()
                        // Navigating to the HomeFragment after successful login
                        findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                    }
                }
            }
        }
    }

    // Navigating to Register fragment
    private fun navToRegister() {
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }
    }

    // Toggle visibility for the password input field
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
