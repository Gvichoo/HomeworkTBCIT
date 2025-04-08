package com.example.homeworktbc.presentation.screen.register

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.databinding.FragmentRegisterBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.presentation.extension.collect
import com.example.homeworktbc.presentation.extension.collectLatest
import com.example.homeworktbc.presentation.screen.register.effect.RegisterEffect
import com.example.homeworktbc.presentation.screen.register.event.RegisterEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun start() {

        setupPasswordToggle(binding.etPasswordRegister, binding.ivEye)
        setupPasswordToggle(binding.etPasswordRepeat, binding.ivEye2)


        setRegisterButtonClickListener()

        observeEffect()

        observeState()
    }

    private fun observeState(){

        collect(viewModel.viewState){
            binding.loader.visibility = if (it.isLoading) View.VISIBLE else View.GONE

            if (it.isSuccess){
                setFragmentResult(
                    "registration_request_key",
                    Bundle().apply {
                        putString("email", binding.etLoginRegister.text.toString())
                        putString("password", binding.etPasswordRegister.text.toString())
                    }
                )
                findNavController().popBackStack()
            }
        }


    }

    private fun observeEffect(){

        collectLatest(viewModel.effects){
            when(it){
                RegisterEffect.NavToLogInFragment -> RegisterEffect.NavToLogInFragment
                is RegisterEffect.ShowError -> showMessage(it.message)
            }
        }


    }


    private fun setRegisterButtonClickListener(){
        binding.btnRegister.setOnClickListener {
            val email = binding.etLoginRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString().trim()
            val passwordRepeated = binding.etPasswordRepeat.text.toString().trim()

            viewModel.obtainEvent(RegisterEvent.SignUpButtonClicked(email,password,passwordRepeated))
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

    private fun showMessage(message : String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }
}