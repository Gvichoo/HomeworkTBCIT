package com.example.homeworktbc.fragmentRegister

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private lateinit var viewModel: RegisterViewModel

    override fun start() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener {

            val email = binding.etLoginRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString().trim()
            val passwordRepeated = binding.etPasswordRepeat.text.toString().trim()


            viewModel.registerUSer(email, password,passwordRepeated) { result ->
                when(result){

                    is Result.Failed -> {
                        Toast.makeText(requireContext(),
                            getString(result.error.errorMessageResource), Toast.LENGTH_SHORT).show()
                    }

                    is Result.IsLoading -> {
                        if(result.isLoading){
                            binding.loader.visibility = View.VISIBLE
                        }else{
                            binding.loader.visibility = View.GONE
                        }
                    }
                    is Result.Success -> {
                        setFragmentResult(
                            "registration_request_key",
                            Bundle().apply {
                                putString("email", email)
                                putString("password", password)
                            }
                        )
                        Toast.makeText(requireContext(), result.result, Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
            }
        }

    }

}

