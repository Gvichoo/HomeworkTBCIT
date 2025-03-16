package com.example.homeworktbc.presentation.fragmentLogin

import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentLogInBinding
import com.example.homeworktbc.presentation.extension.collect
import com.example.homeworktbc.presentation.extension.collectLatest
import com.example.homeworktbc.presentation.fragmentLogin.effect.LoginEffect
import com.example.homeworktbc.presentation.fragmentLogin.event.LoginEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun start() {
        setupPasswordToggle(binding.etPassword, binding.ivEye)

        logInButtonClicked()

        registerButtonClicked()

        receiveEmailAndPasswordFromRegister()

        observeEffect()

        observeState()

    }



    private fun observeState(){
        collect(loginViewModel.viewState){
            binding.loader.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun observeEffect(){
        collectLatest(loginViewModel.effects){
            when(it){
                LoginEffect.NavToHomeFragment -> navToHomeFragment()
                LoginEffect.NavToRegisterFragment -> navToRegisterFragment()
                is LoginEffect.ShowError -> showMessage(it.message)
            }
        }
    }

    private fun logInButtonClicked(){
        binding.btnLogin.setOnClickListener{
            val email = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()
            val rememberMe =  binding.cbRememberMe.isChecked

            loginViewModel.obtainEvent(LoginEvent.LoginButtonClicked(email, password, rememberMe))
        }
    }

    private fun registerButtonClicked() {
        binding.btnRegister.setOnClickListener {
            loginViewModel.obtainEvent(LoginEvent.RegisterButtonClicked)
        }
    }

    private fun navToRegisterFragment() {
        findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
    }


    private fun navToHomeFragment(){
        findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
    }

    private fun receiveEmailAndPasswordFromRegister(){
        setFragmentResultListener("registration_request_key") { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")
            binding.etLogin.setText(email)
            binding.etPassword.setText(password)
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

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}