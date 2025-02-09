package com.example.homeworktbc.fragmentRegister

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private lateinit var viewModel: RegisterViewModel

    override fun start() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        setupPasswordToggle(binding.etPasswordRegister, binding.ivEye)
        setupPasswordToggle(binding.etPasswordRepeat, binding.ivEye2)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect { state ->
                    when {
                        state.loading -> {
                            binding.loader.visibility = View.VISIBLE
                        }
                        state.success != null -> {
                            binding.loader.visibility = View.GONE
                            Toast.makeText(requireContext(), state.success, Toast.LENGTH_SHORT).show()

                            setFragmentResult(
                                "registration_request_key",
                                Bundle().apply {
                                    putString("email", binding.etLoginRegister.text.toString())
                                    putString("password", binding.etPasswordRegister.text.toString())
                                }
                            )
                            findNavController().popBackStack()
                        }
                        state.error != null -> {
                            binding.loader.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                getString(state.error.errorMessageResource),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.etLoginRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString().trim()
            val passwordRepeated = binding.etPasswordRepeat.text.toString().trim()

            viewModel.registerUser(email, password, passwordRepeated)
        }
    }
    private fun setupPasswordToggle(editText: AppCompatEditText, toggleButton: AppCompatImageButton) {
        toggleButton.setOnClickListener {
            if (editText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            editText.setSelection(editText.text?.length ?: 0)
        }
    }
}
