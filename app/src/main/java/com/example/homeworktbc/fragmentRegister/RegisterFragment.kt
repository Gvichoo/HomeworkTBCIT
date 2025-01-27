package com.example.homeworktbc.fragmentRegister

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private lateinit var viewModel: RegisterViewModel

    override fun start() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        lifecycleScope.launchWhenStarted {
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
                        Toast.makeText(requireContext(), getString(state.error.errorMessageResource), Toast.LENGTH_SHORT).show()
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
}
